package chatty.gateway;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.answer_selection.AnswerSelectionService;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.gateway.types.TextClassificationPredictionRequest;
import chatty.gateway.types.TextClassificationPredictionResult;
import chatty.intents.IntentEntity;
import chatty.intents.IntentService;
import chatty.training.TrainingType;
import chatty.util.exceptions.ChattyException;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class AnswerSelectionGatewayService {

    @Inject
    private ChattyNLPClient chattyNLPClient;

    @Inject
    private AnswerSelectionService answerSelectionService;

    @Inject
    private IntentService intentService;

    @Inject
    private TextClassificationGatewayService textClassificationGatewayService;

    @Inject
    private ClassifierService classifierService;


    @Transactional
    public void train(final String answerSelectionName, final Integer epochs, final String userName) {
        final AnswerSelectionTO answerSelectionTO = answerSelectionService.findByAnswerSelectionName(
                answerSelectionName, userName);
        chattyNLPClient.train(answerSelectionTO.getId(), epochs);
    }


    @Transactional(readOnly = true)
    public String predict(final String answerSelectionName, final String classifierName,
                          final String userName,
                          final List<String> inputs) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Could not find Classifier with name" + classifierName));
        final TextClassificationPredictionRequest textClassificationPredictionRequest = new TextClassificationPredictionRequest();
        textClassificationPredictionRequest.setInputs(inputs);
        textClassificationPredictionRequest.setType(Arrays.asList(TrainingType.CNN));
        final List<TextClassificationPredictionResult> predictionResult = textClassificationGatewayService.predict(
                classifierEntity.getClassifierName(),
                textClassificationPredictionRequest, userName);
        if (predictionResult.isEmpty()) {
            throw new ChattyException(
                    "No prediction possible for classifier " + classifierName + " and answer selection module " + answerSelectionName);

        }

        final AnswerSelectionTO answerSelectionTO = answerSelectionService.findByAnswerSelectionName(
                answerSelectionName, userName);
        final String intentName = predictionResult.get(0).getPredictions().get(0);
        final IntentEntity intentEntity = intentService.findIntentByNameAndClassifierId(
                intentName, classifierEntity.getId()).orElseThrow(
                () -> new ChattyException("Could not find intent with name " + intentName));
        return chattyNLPClient.predict(answerSelectionTO.getId(), intentEntity.getIntentName(), inputs).body();


    }
}
