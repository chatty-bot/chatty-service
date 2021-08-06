package chatty_woz_data.dialogue.types;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_dialog_data")
public class DialogueDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer turn;


    @Column
    private String transcript;

    @Column(name = "system_transcript")
    private String systemTranscript;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id")
    private DialogueEntity dialogueEntity;


    @OneToOne(mappedBy = "dialogueDataEntity", cascade = CascadeType.ALL)
    private StateEntity stateEntity;

    @Enumerated(EnumType.STRING)
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


    public DialogueEntity getDialogueEntity() {
        return dialogueEntity;
    }


    public void setDialogueEntity(final DialogueEntity dialogueEntity) {
        this.dialogueEntity = dialogueEntity;
    }


    public StateEntity getStateEntity() {
        return stateEntity;
    }


    public void setStateEntity(final StateEntity stateEntity) {
        this.stateEntity = stateEntity;
    }


    public Issuer getIssuer() {
        return issuer;
    }


    public void setIssuer(final Issuer issuer) {
        this.issuer = issuer;
    }
}
