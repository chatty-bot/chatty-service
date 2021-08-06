package chatty_woz_data.dialogue.boundary;

import javax.inject.Inject;
import chatty_woz_data.dialogue.control.DialogueService;
import chatty_woz_data.dialogue.types.DialogueDataTO;
import chatty_woz_data.dialogue.types.DialogueTO;
import chatty_woz_data.dialogue.types.DialogueUpdateTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/dialogue")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class DialogueController {

    @Inject
    private DialogueService dialogueService;


    @Post
    public HttpResponse createDialogue(final DialogueTO dialogueTO) {
        dialogueService.createDialogue(dialogueTO);
        return HttpResponse.ok();
    }


    @Post("add")
    public HttpResponse addDialogueDataToDialogue(DialogueUpdateTO dialogueUpdateTO) {
        dialogueService.addDialogueDataToDialogue(dialogueUpdateTO);
        return HttpResponse.ok();
    }


    @Post("update")
    public HttpResponse updateDialogdataInDialogue(DialogueUpdateTO dialogueUpdateTO) {
        dialogueService.updateDialogueDataInDialogue(dialogueUpdateTO);
        return HttpResponse.ok();
    }


    @Post("{dialogueId}")
    public void addDialogData(@PathVariable final Integer dialogueId, final DialogueDataTO dialogueDataTO) {

    }


    @Get("user")
    public DialogueTO findLatestDialogueRequiresUser() {
        return dialogueService.findLatestDialogueRequiresUser();
    }


    @Get("system")
    public DialogueTO findLatestDialogueRequiresSystem() {
        return dialogueService.findLatestDialogueRequiresSystem();

    }
}
