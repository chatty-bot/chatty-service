package chatty_woz_data.dialogue.types;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import chatty_woz_data.dialogue.types.DialogueDataEntity;
import chatty_woz_data.dialogue.types.SlotEntity;

@Table(name = "t_state")
@Entity
public class StateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL)
    private List<SlotEntity> slots = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "dialog_data_id")
    private DialogueDataEntity dialogueDataEntity;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public List<SlotEntity> getSlots() {
        return slots;
    }


    public void setSlots(final List<SlotEntity> slots) {
        this.slots = slots;
    }


    public DialogueDataEntity getDialogueDataEntity() {
        return dialogueDataEntity;
    }


    public void setDialogueDataEntity(final DialogueDataEntity dialogueDataEntity) {
        this.dialogueDataEntity = dialogueDataEntity;
    }
}
