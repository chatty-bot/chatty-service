package chatty.gateway.types;

import java.util.Map;

public class StatePredictionResult {
    private String slotName;
    private Map<String, Float> statePredictions;


    public Map<String, Float> getStatePredictions() {
        return statePredictions;
    }


    public void setStatePredictions(final Map<String, Float> statePredictions) {
        this.statePredictions = statePredictions;
    }


    public String getSlotName() {
        return slotName;
    }


    public void setSlotName(final String slotName) {
        this.slotName = slotName;
    }
}
