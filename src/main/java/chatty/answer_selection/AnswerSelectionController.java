package chatty.answer_selection;


import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import chatty.answer_selection.types.AnswerSelectionTO;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * I'm the REST endpoint for accessing answer selection modules
 */
@Controller("/answer_selection")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AnswerSelectionController {

    @Inject
    private AnswerSelectionService answerSelectionService;


    /**
     * Get all answers selection modules for a particular user
     *
     * @param principal current user
     * @return a list of all answer selection modules for that particular user
     */
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnswerSelectionTO> getAllAnswerSelectionForUser(Principal principal) {
        return answerSelectionService.findAllForUser(principal.getName());
    }


    /**
     * Get all answers selection modules for a particular user
     *
     * @param principal current user
     * @return a list of all answer selection modules for that particular user
     */
    @Get("/{answerSelectionName}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerSelectionTO getAnswerSelectionByName(@PathVariable String answerSelectionName,
                                                      Principal principal) {
        return answerSelectionService.findByAnswerSelectionName(answerSelectionName, principal.getName());
    }


    /**
     * Create a new answer selection module
     *
     * @param principal             the current user
     * @param answerSelectionModule the name of the answer selection module
     * @return the newly created answer selection entity
     */
    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerSelectionTO createAnswerSelectionModule(Principal principal,
                                                         final AnswerSelectionTO answerSelectionModule) {
        return answerSelectionService.create(principal.getName(), answerSelectionModule.getAnswerSelectionName());
    }


}
