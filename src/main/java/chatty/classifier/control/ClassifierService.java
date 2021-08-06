package chatty.classifier.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.classifier.entity.ClassifierRepository;
import chatty.classifier.types.ClassifierDataSampleTO;
import chatty.classifier.types.ClassifierDomainObject;
import chatty.classifier.types.ClassifierEntity;
import chatty.classifier.types.ClassifierTO;
import chatty.intents.IntentEntity;
import chatty.intents.IntentService;
import chatty.intents.IntentTO;
import chatty.metrics.MetricsEntity;
import chatty.metrics.MetricsMapper;
import chatty.metrics.MetricsService;
import chatty.training.TrainingEntity;
import chatty.training.TrainingService;
import chatty.upload.control.TextClassificationDataSampleUploadService;
import chatty.upload.control.TextClassificationDatasetService;
import chatty.upload.types.TextClassificationDataSampleEntity;
import chatty.upload.types.TextClassificationDatasetEntity;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import chatty.util.exceptions.UserNotFoundException;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class ClassifierService {

    @Inject
    private ClassifierRepository classifierRepository;

    @Inject
    private UserService userService;

    @Inject
    private MetricsService metricsService;

    @Inject
    private IntentService intentService;

    @Inject
    private TextClassificationDatasetService textClassificationDatasetService;

    @Inject
    private TrainingService trainingService;
    @Inject
    private MetricsMapper metricsMapper;


    @Inject
    private ClassifierMapper classifierMapper;

    @Inject
    private TextClassificationDataSampleUploadService textClassificationDataSampleUploadService;


    @Transactional
    public ClassifierEntity create(final String userName, final String classifierName) {
        UserEntity user = userService.findByUserName(userName).orElseThrow(
                UserNotFoundException::new);
        ClassifierEntity classifierEntity = new ClassifierEntity();
        classifierEntity.setClassifierName(classifierName);
        classifierEntity.setUserEntity(user);
        return classifierRepository.persist(classifierEntity);
    }


    @Transactional(readOnly = true)
    public Optional<ClassifierEntity> findClassifierByName(final String classifierName) {
        return classifierRepository.findByClassifierName(classifierName);
    }


    @Transactional(readOnly = true)
    public ClassifierDomainObject extractClassifierDomainObject(final String classifierName) {
        final ClassifierEntity classifierEntity = classifierRepository.findByClassifierName(
                classifierName).orElseThrow(
                () -> new ChattyException("Classifier with name " + classifierName + " not found"));
        // extract all metrics
        final List<MetricsEntity> metrics = metricsService.findMetricsByClassifierId(classifierEntity.getId());
        final List<IntentTO> intents = intentService.findIntentsByClassifierId(classifierEntity.getId());
        final List<TextClassificationDatasetEntity> datasets = textClassificationDatasetService.findDatasetsByClassifierId(
                classifierEntity.getId());
        final List<TrainingEntity> trainings = trainingService.findTrainingByClassifierId(classifierEntity.getId());

        final ClassifierDomainObject classifierDomainObject = new ClassifierDomainObject();
        classifierDomainObject.setClassifierName(classifierEntity.getClassifierName());
        classifierDomainObject.setMetrics(metrics.stream().map(metricsMapper::toTO).collect(
                Collectors.toList()));
        classifierDomainObject.setIntents(intents);
        classifierDomainObject.setDatasets(datasets);
        classifierDomainObject.setTrainings(trainings);
        return classifierDomainObject;
    }


    @Transactional(readOnly = true)
    public List<ClassifierTO> findAllByUser(final String userName) {
        return classifierRepository.findAllByUser(userName).stream().map(classifierMapper::toTO).collect(
                Collectors.toList());
    }


    @Transactional
    public TextClassificationDataSampleEntity addDataSample(final String classifierName,
                                                            final ClassifierDataSampleTO dataSampleTO,
                                                            final String userName) {
        UserEntity user = userService.findByUserName(userName).orElseThrow(
                UserNotFoundException::new);
        final ClassifierEntity classifierEntity = classifierRepository.findByClassifierName(
                classifierName).orElseThrow(
                () -> new ChattyException("Classifier with name " + classifierName + " not found"));

        final IntentEntity intentEntity = intentService.findIntentByNameAndClassifierId(
                dataSampleTO.getLabel(), classifierEntity.getId()).orElseThrow(
                () -> new ChattyException("Could not find intent"));
        intentEntity.incrementNumberOfSamples();

        final TextClassificationDataSampleEntity sampleEntity = new TextClassificationDataSampleEntity();
        sampleEntity.setClassifierId(classifierEntity.getId());
        sampleEntity.setLabel(dataSampleTO.getLabel());
        sampleEntity.setText(dataSampleTO.getText());
        sampleEntity.setUserId(user.getId());


        return textClassificationDataSampleUploadService.persist(sampleEntity);
    }
}
