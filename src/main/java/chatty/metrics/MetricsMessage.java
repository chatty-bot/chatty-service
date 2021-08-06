package chatty.metrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricsMessage {

    @JsonProperty("intent_name")
    private String intentName;

    private float precision;


    public String getIntentName() {
        return intentName;
    }


    public void setIntentName(final String intentName) {
        this.intentName = intentName;
    }


    public float getPrecision() {
        return precision;
    }


    public void setPrecision(final float precision) {
        this.precision = precision;
    }


}