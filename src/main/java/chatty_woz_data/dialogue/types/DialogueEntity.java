package chatty_woz_data.dialogue.types;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_dialogs")
public class DialogueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(mappedBy = "dialogueEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DialogueDataEntity> dialogData = new ArrayList<>();


    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(name = "requires_action")
    @Enumerated(EnumType.STRING)
    private Issuer requiresActionFrom;


    public boolean isFinished() {
        return isFinished;
    }


    public void setFinished(final boolean finished) {
        isFinished = finished;
    }


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public List<DialogueDataEntity> getDialogData() {
        return dialogData;
    }


    public void setDialogData(final List<DialogueDataEntity> dialogData) {
        this.dialogData = dialogData;
    }


    public Issuer getRequiresActionFrom() {
        return requiresActionFrom;
    }


    public void setRequiresActionFrom(final Issuer requiresActionFrom) {
        this.requiresActionFrom = requiresActionFrom;
    }


    public void addDialogData(final DialogueDataEntity dialogueDataEntity) {
        dialogueDataEntity.setDialogueEntity(this);
        dialogData.add(dialogueDataEntity);
    }
}
