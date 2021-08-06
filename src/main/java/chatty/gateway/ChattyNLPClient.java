package chatty.gateway;

import java.util.List;
import java.util.Map;
import chatty.gateway.types.TextClassificationPredictionRequest;
import chatty.gateway.types.TextClassificationPredictionResult;
import chatty.metrics.MetricsMessage;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;

@Client("http://localhost:5000")
public interface ChattyNLPClient {

    @Get("/text_classification//train/{classifierId}")
    HttpResponse<List<MetricsMessage>> train(@PathVariable final long classifierId,
                                             @QueryValue(value = "training_type") String trainingtype,
                                             @QueryValue(value = "epochs", defaultValue = "0") Integer epochs);


    @Post("/text_classification/predict/{classifierId}")
    HttpResponse<List<TextClassificationPredictionResult>> predict(@PathVariable final long classifierId,
                                                                   @Body TextClassificationPredictionRequest textClassificationPredictionRequest);

    @Get("/answer_selection/train/{answerSelectionId}")
    HttpResponse train(@PathVariable final int answerSelectionId,
                       @QueryValue(value = "epochs", defaultValue = "0") Integer epochs);

    @Post("/answer_selection/predict/{answerSelectionId}")
    HttpResponse<String> predict(@PathVariable final int answerSelectionId,
                                 @QueryValue(value = "intent_name") final String intentName,
                                 final List<String> inputs);


    @Get("/dst/trainDst")
    public void trainDialogueStateTracking(@QueryValue(value = "epochs", defaultValue = "0") final Integer epochs,
                                           @QueryValue final String fileName, @QueryValue final String userId,
                                           @QueryValue final String dstId);


    @Post("/dst/predictDst")
    public HttpResponse predictDialogueStateTracking(
            final String transcript, final List<String> systemActs,
            final Map<String, Map<String, String>> beliefState,
            @QueryValue final String fileName, @QueryValue final String userId, @QueryValue final String dstId);

    @Post(value = "/dst/upload", produces = MediaType.MULTIPART_FORM_DATA)
    public String uploadDst(
            @Body final MultipartBody file);


    @Get("/seq2seq/trainSeq2Seq")
    public void trainSeq2Seq(@QueryValue(value = "epochs", defaultValue = "0") final Integer epochs,
                             @QueryValue(value = "fileName") final String fileName, @QueryValue final String seq2SeqId);

    @Post("/seq2seq/predictSeq2Seq")
    public String predictSeq2Seq(
            final String transcript, @QueryValue final String seq2SeqId);

    @Post(value = "/seq2seq/upload", produces = MediaType.MULTIPART_FORM_DATA)
    public String uploadSeq2Seq(
            @Body final MultipartBody file);


}
