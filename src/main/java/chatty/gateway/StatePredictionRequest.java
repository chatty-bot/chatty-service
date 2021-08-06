package chatty.gateway;

import java.util.ArrayList;
import java.util.List;

public class StatePredictionRequest {
    private List<String> systemActs = new ArrayList<>();
    private String transcript;


    public List<String> getSystemActs() {
        return systemActs;
    }


    public void setSystemActs(final List<String> systemActs) {
        this.systemActs = systemActs;
    }


    public String getTranscript() {
        return transcript;
    }


    public void setTranscript(final String transcript) {
        this.transcript = transcript;
    }
}
