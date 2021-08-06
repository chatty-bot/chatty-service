package chatty_woz_data.dialogue.types;

public class DialogueUpdateTO {

    private Integer dialogId;
    private DialogueDataTO dialogueDataTO;
    private boolean finished;
    private Issuer requiresActionFrom;


    public Integer getDialogId() {
        return dialogId;
    }


    public void setDialogId(final Integer dialogId) {
        this.dialogId = dialogId;
    }


    public DialogueDataTO getDialogueDataTO() {
        return dialogueDataTO;
    }


    public void setDialogueDataTO(final DialogueDataTO dialogueDataTO) {
        this.dialogueDataTO = dialogueDataTO;
    }


    public boolean isFinished() {
        return finished;
    }


    public void setFinished(final boolean finished) {
        this.finished = finished;
    }


    public Issuer getRequiresActionFrom() {
        return requiresActionFrom;
    }


    public void setRequiresActionFrom(final Issuer requiresActionFrom) {
        this.requiresActionFrom = requiresActionFrom;
    }
}
