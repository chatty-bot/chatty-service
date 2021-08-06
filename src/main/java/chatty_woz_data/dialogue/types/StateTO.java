package chatty_woz_data.dialogue.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import chatty_woz_data.dialogue.types.SlotTO;

public class StateTO {

    private Integer id;
    @NotEmpty
    private List<SlotTO> slots = new ArrayList<>();
    private Integer dialogDataId;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public List<SlotTO> getSlots() {
        return slots;
    }


    public void setSlots(final List<SlotTO> slots) {
        this.slots = slots;
    }


    public Integer getDialogDataId() {
        return dialogDataId;
    }


    public void setDialogDataId(final Integer dialogDataId) {
        this.dialogDataId = dialogDataId;
    }
}
