package chatty.gateway.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import chatty.training.TrainingType;

/**
 * This is used for predicting a classification of the given inputs for each type that is used
 */
public class TextClassificationPredictionRequest {

    @NotNull
    private List<String> inputs = new ArrayList<>();

    @NotNull
    private List<TrainingType> type = new ArrayList<>();


    public List<String> getInputs() {
        return inputs;
    }


    public void setInputs(final List<String> inputs) {
        this.inputs = inputs;
    }


    public List<TrainingType> getType() {
        return type;
    }


    public void setType(final List<TrainingType> type) {
        this.type = type;
    }
}
