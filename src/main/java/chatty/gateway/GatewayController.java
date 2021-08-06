package chatty.gateway;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import chatty.chatbot.ChatbotService;
import chatty.dst.types.DstTO;
import chatty.gateway.types.ChatbotRequest;
import chatty.gateway.types.TextClassificationPredictionRequest;
import chatty.gateway.types.TextClassificationPredictionResult;
import chatty.metrics.MetricsError;
import chatty.seq2seq.Seq2SeqTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * I'm the REST endpoint for the gateway to the nlp service. I provide endpoints for training and prediction nlp models.
 */
@Controller("/gateway")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class GatewayController {

    @Inject
    private TextClassificationGatewayService textClassificationGatewayService;

    @Inject
    private AnswerSelectionGatewayService answerSelectionGatewayService;

    @Inject
    private DialogueStateTrackingGatewayService dialogueStateTrackingGatewayService;


    @Inject
    private Seq2SeqGatewayService seq2SeqGatewayService;


    @Inject
    private ChatbotService chatbotService;


    /**
     * Train a text classification model with the given name and a required training type. Optionally the epochs can be specified which determine how many epochs the model will be trained
     *
     * @param classifierName the name of the text classification model
     * @param trainingtype   the type of training that will be done. Can be SVM or CNN
     * @param epochs         how many epochs the model will be trained
     * @param principal      the current user
     * @return a list of error if the training has failed
     */
    @Get("/train/{classifierName}")
    public List<MetricsError> trainTextClassification(@PathVariable final String classifierName,
                                                      @QueryValue(value = "training_type") String trainingtype,
                                                      @QueryValue(value = "epochs", defaultValue = "0") Integer epochs,
                                                      final Principal principal) {
        return textClassificationGatewayService.train(classifierName, trainingtype, epochs,
                principal.getName());

    }


    /**
     * Predict a intent for a given text classification model specified by its training type and name.
     *
     * @param classifierName                      the name of the text classification model
     * @param textClassificationPredictionRequest the request containing all the information needed for a prediction. Like inputs and training type {@link TextClassificationPredictionRequest}
     * @param principal                           the current user
     * @return the predicted result
     */
    @Post(value = "/predict/{classifierName}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public List<TextClassificationPredictionResult> predictTextClassification(@PathVariable final String classifierName,
                                                                              final TextClassificationPredictionRequest textClassificationPredictionRequest,
                                                                              final Principal principal) {
        return textClassificationGatewayService.predict(classifierName, textClassificationPredictionRequest,
                principal.getName());

    }


    /**
     * Train a particular answer selection module specified by name
     *
     * @param answerSelectionName the name of the answer selection module
     * @param epochs              how many epochs the model will be trained
     * @param principal           the current user
     * @return no content if the training was successful
     */
    @Get("answer_selection/train/{answerSelectionName}")
    public HttpResponse trainAnswerSelectionModule(@PathVariable final String answerSelectionName,
                                                   @QueryValue(value = "epochs", defaultValue = "0") Integer epochs,
                                                   final Principal principal) {
        answerSelectionGatewayService.train(answerSelectionName, epochs,
                principal.getName());
        return HttpResponse.noContent();
    }


    /**
     * Predict an answer with the help of an answer selection module specified by its name
     *
     * @param answerSelectionName the name of the answer selection module
     * @param classifierName      the name of the classifier that will be used to determine which intent is the input (to reduce answer selection computation)
     * @param inputs              the input for which an answer will be selected
     * @param principal           the current user
     * @return the answer prediction for the given input
     */
    @Post("answer_selection/predict/{answerSelectionName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String predictAnswerSelectionModule(@PathVariable final String answerSelectionName,
                                               @QueryValue(value = "classifier_name") final String classifierName,
                                               final List<String> inputs,
                                               final Principal principal) {
        return answerSelectionGatewayService.predict(answerSelectionName, classifierName, principal.getName(), inputs);

    }


    @Get("/dst/train/{dstId}")
    public void trainDialogueStateTracking(
            @QueryValue(value = "epochs", defaultValue = "0") final Integer epochs, final String dstId,
            final Principal principal) {
        dialogueStateTrackingGatewayService.trainDst(epochs, dstId, principal.getName());
    }


    @Post(value = "/dst/upload/{dstName}", consumes = MediaType.MULTIPART_FORM_DATA)
    public DstTO uploadDst(@PathVariable(name = "dstName") final String dstName,
                           final CompletedFileUpload file, final Principal principal) {
        return dialogueStateTrackingGatewayService.uploadDst(dstName, file, principal.getName());
    }


    @Post("/dst/predict/{dstId}")
    public HttpResponse predictDialogueStateTracking(
            @Parameter(hidden = true) final String transcript, @Parameter(hidden = true) final List<String> systemActs,
            @Parameter(hidden = true) final Map<String, Map<String, String>> beliefState, final String dstId,
            final Principal principal) {
        return dialogueStateTrackingGatewayService.predictDst(transcript, systemActs, beliefState, dstId,
                principal.getName());
    }


    @Get("/seq2seq/train/{seq2SeqId}")
    public Maybe<Seq2SeqTO> trainSeq2Seq(
            @QueryValue(value = "epochs", defaultValue = "0") final Integer epochs,
            @QueryValue(value = "seq2SeqName") @NotBlank final String seq2SeqName, final String seq2SeqId,
            final Principal principal) {
        return Maybe.just(seq2SeqGatewayService.trainSeq2Seq(epochs, seq2SeqName, principal.getName(), seq2SeqId))
                .subscribeOn(Schedulers.io());
    }


    @Post("/seq2seq/predict/{seq2SeqId}")
    public String predictSeq2Seq(
            final String transcript, final String seq2SeqId) {
        return seq2SeqGatewayService.predictSeq2Seq(transcript, seq2SeqId);
    }


    @Post(value = "/seq2seq/upload/{seq2seqName}", consumes = MediaType.MULTIPART_FORM_DATA)
    public Seq2SeqTO uploadSeq2Seq(@PathVariable(name = "seq2seqName") final String seq2SeqName,
                                   final CompletedFileUpload file, final Principal principal) {
        return seq2SeqGatewayService.uploadSeq2Seq(seq2SeqName, file, principal.getName());
    }


    @Post("/chatbot")
    public HttpResponse processMessage(
            final ChatbotRequest chatbotRequest) {
        // TODO how to get the system acts its not like I have the information at runtime, so the information has to be infered
        return chatbotService.processRequest(chatbotRequest);
    }

}
