package chatty.training;


import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import chatty.answer_selection.AnswerSelectionService;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.util.exceptions.ChattyException;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/trainings")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TrainingController {


    @Inject
    private TrainingService trainingService;

    @Inject
    private ClassifierService classifierService;

    @Inject
    private AnswerSelectionService answerSelectionService;


    @Get("/text_classification/{classifierName}")
    public List<TrainingEntity> fetchByClassifierName(
            @PathVariable(name = "classifierName") final String classifierName,
            final Principal principal) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Could not find classifier wit name " + classifierName));
        return trainingService.findTrainingByClassifierId(classifierEntity.getId());
    }


    @Get("/answer_selection/{answerSelectionName}")
    public List<TrainingEntity> fetchByAnswerSelectionName(
            @PathVariable(name = "answerSelectionName") final String answerSelectionName,
            final Principal principal) {
        final AnswerSelectionTO answerSelectionTO = answerSelectionService.findByAnswerSelectionName(
                answerSelectionName, principal.getName());
        return trainingService.findTrainingByAnswerSelectionId(answerSelectionTO.getId());
    }
}
