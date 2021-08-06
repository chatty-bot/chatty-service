package chatty.upload.boundary;


import java.security.Principal;
import javax.inject.Inject;
import chatty.upload.control.UploadService;
import chatty.upload.control.annotations.AnswerSelectionUpload;
import chatty.upload.control.annotations.TextClassificationUpload;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/upload")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UploadController {

    public static boolean SKIP_HEADERS = false;


    @Inject
    @TextClassificationUpload
    private UploadService textClassificationUpload;

    @Inject
    @AnswerSelectionUpload
    private UploadService answerSelectionUpload;


    @Post(value = "text_classification/{classifierName}", consumes = MediaType.MULTIPART_FORM_DATA)
    public void uploadClassifierData(@PathVariable String classifierName, Principal principal,
                                     CompletedFileUpload file) {
        textClassificationUpload.process(classifierName, principal.getName(), file);
    }


    @Post(value = "answer_selection/{answerSelectionName}", consumes = MediaType.MULTIPART_FORM_DATA)
    public void uploadAnswerSelectionData(@PathVariable String answerSelectionName, Principal principal,
                                          CompletedFileUpload file) {
        answerSelectionUpload.process(answerSelectionName, principal.getName(), file);

    }

}
