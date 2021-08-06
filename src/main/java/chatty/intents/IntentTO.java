package chatty.intents;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IntentTO {
    private Integer id;

    @NotBlank
    private String intentName;
    private int classifierId;
    @NotNull
    private int numberOfSamples;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getIntentName() {
        return intentName;
    }


    public void setIntentName(final String intentName) {
        this.intentName = intentName;
    }


    public int getClassifierId() {
        return classifierId;
    }


    public void setClassifierId(final int classifierId) {
        this.classifierId = classifierId;
    }


    public int getNumberOfSamples() {
        return numberOfSamples;
    }


    public void setNumberOfSamples(final int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }
}
