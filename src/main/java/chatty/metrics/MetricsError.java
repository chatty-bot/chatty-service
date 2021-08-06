package chatty.metrics;

import com.fasterxml.jackson.annotation.JsonGetter;

public class MetricsError {
    private String intentName;
    private String errorMessage = "Error saving metrics for intent {0}: {0} does not exist";


    public MetricsError(final String intentName) {
        this.intentName = intentName;
    }


    @JsonGetter("error")
    public String getErrorMessage() {
        return String.format(this.errorMessage, this.intentName);
    }
}
