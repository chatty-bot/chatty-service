package chatty_woz_data.dialogue.types;

import javax.validation.constraints.NotNull;

public class DialogueDataTO {

    private Integer id;
    @NotNull
    private Integer turn;

    private String transcript;
    private String systemTranscript;
    private Integer dialogueId;
    private StateTO stateTO = new StateTO();
    private Issuer issuer;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getTurn() {
        return turn;
    }


    public void setTurn(final Integer turn) {
        this.turn = turn;
    }


    public String getTranscript() {
        return transcript;
    }


    public void setTranscript(final String transcript) {
        this.transcript = transcript;
    }


    public String getSystemTranscript() {
        return systemTranscript;
    }


    public void setSystemTranscript(final String systemTranscript) {
        this.systemTranscript = systemTranscript;
    }


    public Integer getDialogueId() {
        return dialogueId;
    }


    public void setDialogueId(final Integer dialogueId) {
        this.dialogueId = dialogueId;
    }


    public StateTO getStateTO() {
        return stateTO;
    }


    public void setStateTO(final StateTO stateTO) {
        this.stateTO = stateTO;
    }


    public Issuer getIssuer() {
        return issuer;
    }


    public void setIssuer(final Issuer issuer) {
        this.issuer = issuer;
    }
}
