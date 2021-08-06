package chatty.gateway.types;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import chatty.training.TrainingType;

public class TextClassificationPredictionResult {

    @JsonProperty("model_type")
    private TrainingType trainingType;

    private List<String> predictions = new ArrayList<>();


    public TrainingType getTrainingType() {
        return trainingType;
    }


    public void setTrainingType(final TrainingType trainingType) {
        this.trainingType = trainingType;
    }


    public List<String> getPredictions() {
        return predictions;
    }


    public void setPredictions(final List<String> predictions) {
        this.predictions = predictions;
    }
}
