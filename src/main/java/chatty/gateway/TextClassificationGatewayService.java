package chatty.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.gateway.types.TextClassificationPredictionRequest;
import chatty.gateway.types.TextClassificationPredictionResult;
import chatty.intents.IntentEntity;
import chatty.intents.IntentService;
import chatty.metrics.MetricsEntity;
import chatty.metrics.MetricsError;
import chatty.metrics.MetricsMapper;
import chatty.metrics.MetricsMessage;
import chatty.metrics.MetricsService;
import chatty.training.TrainingEntity;
import chatty.training.TrainingService;
import chatty.training.TrainingStatus;
import chatty.training.TrainingType;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import io.micronaut.http.HttpResponse;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class TextClassificationGatewayService {


    @Inject
    private ClassifierService classifierService;
    @Inject
    private ChattyNLPClient chattyNLPClient;

    @Inject
    private MetricsMapper metricsMapper;

    @Inject
    private IntentService intentService;

    @Inject
    private MetricsService metricsService;

    @Inject
    private UserService userService;

    @Inject
    private TrainingService trainingService;


    @Transactional
    public List<MetricsError> train(final String classifierName, final String trainingType, final Integer epochs,
                                    final String userName) {

        // check if user exists
        final Optional<UserEntity> userEntity = userService.findByUserName(userName);
        if (!userEntity.isPresent()) {
            throw new ChattyException("User with name " + userName + " not present");
        }

        // check if classifier exists and perform "/trainDst/{classifierId} on nlp-service
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> {
                    return new ChattyException("Classifier wit name " + classifierName + " not found");
                });

        // check if training already exists
        final List<TrainingEntity> trainingEntities = trainingService.findTrainingByClassifierId(
                classifierEntity.getId());
        trainingEntities.forEach(trainingEntity -> {
            if (trainingEntity.getTrainingStatus().equals(TrainingStatus.IN_PROGRESS)) {
                throw new ChattyException("Cannot start new training until the last training has finished");
            }
        });

        // perform request
        final HttpResponse<List<MetricsMessage>> request = chattyNLPClient.train(classifierEntity.getId(),
                trainingType, epochs);

        // extract body in blocking manner from http response
        final Optional<List<MetricsMessage>> metricsMessagesOptional = request.getBody();

        // initialize error list if some metrics do not match our internal database model
        List<MetricsError> errors = new ArrayList<>();

        // if message body is present
        if (metricsMessagesOptional.isPresent()) {

            final List<MetricsMessage> metricsMessages = metricsMessagesOptional.get();
            // check if metrics for the training type exists
            metricsMessages.forEach(metricsMessage -> {
                // Find the corresponding intents that are mentionend in the metrics object
                final Optional<IntentEntity> intentEntity = intentService.findIntentByNameAndClassifierId(
                        metricsMessage.getIntentName(), classifierEntity.getId());
                if (intentEntity.isPresent()) {
                    // if intent exists check if there exists already a metric that corresponds to this intent. If yes then override
                    final Optional<MetricsEntity> metricsByIntentAndClassifier = metricsService.findByIntentAndClassifierAndTrainingType(
                            intentEntity.get().getId(), classifierEntity.getId(), TrainingType.valueOf(trainingType));
                    if (metricsByIntentAndClassifier.isPresent()) {
                        final MetricsEntity metricsEntity = metricsByIntentAndClassifier.get();
                        metricsEntity.setPrecision(metricsMessage.getPrecision());
                        metricsService.updateMetric(metricsEntity);
                    } else {
                        // else create a new metrics object
                        final MetricsEntity metricsEntity = metricsMapper.toEntity(metricsMessage);
                        metricsEntity.setClassifierId(classifierEntity.getId());
                        metricsEntity.setIntentId(intentEntity.get().getId());
                        metricsEntity.setTrainingType(TrainingType.valueOf(trainingType));
                        metricsService.createMetric(metricsEntity);
                    }

                } else {
                    // intent was not present , so add new error objects to response
                    final MetricsError metricsError = new MetricsError(metricsMessage.getIntentName());
                    errors.add(metricsError);
                }
            });
        }
        return errors;
    }


    @Transactional(readOnly = true)
    public List<TextClassificationPredictionResult> predict(final String classifierName,
                                                            final TextClassificationPredictionRequest textClassificationPredictionRequest,
                                                            final String userName) {
        // check if user exists
        final Optional<UserEntity> userEntity = userService.findByUserName(userName);
        if (!userEntity.isPresent()) {
            throw new ChattyException("User with name " + userName + " not present");
        }
        // check if classifier exists and perform "/trainDst/{classifierId} on nlp-service
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> {
                    return new ChattyException("Classifier with name " + classifierName + " not found");
                });
        return chattyNLPClient.predict(classifierEntity.getId(), textClassificationPredictionRequest).body();
    }

}
