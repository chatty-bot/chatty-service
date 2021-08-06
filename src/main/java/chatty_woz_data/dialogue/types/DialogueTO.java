package chatty_woz_data.dialogue.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;

public class DialogueTO {

    private Integer id;
    @NotEmpty
    private List<DialogueDataTO> dialogData = new ArrayList<>();

    private boolean isFinished;

    private Issuer requiresActionFrom;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public List<DialogueDataTO> getDialogData() {
        return dialogData;
    }


    public void setDialogData(final List<DialogueDataTO> dialogData) {
        this.dialogData = dialogData;
    }


    public boolean isFinished() {
        return isFinished;
    }


    public void setFinished(final boolean finished) {
        isFinished = finished;
    }


    public Issuer getRequiresActionFrom() {
        return requiresActionFrom;
    }


    public void setRequiresActionFrom(final Issuer requiresActionFrom) {
        this.requiresActionFrom = requiresActionFrom;
    }
}
