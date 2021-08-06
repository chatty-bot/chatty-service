package chatty_woz_data.dialogue.types;

import javax.validation.constraints.NotBlank;

public class SlotTO {

    private Integer id;
    private Integer stateId;
    @NotBlank
    private String label;
    @NotBlank
    private String value;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getStateId() {
        return stateId;
    }


    public void setStateId(final Integer stateId) {
        this.stateId = stateId;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(final String label) {
        this.label = label;
    }


    public String getValue() {
        return value;
    }


    public void setValue(final String value) {
        this.value = value;
    }
}
