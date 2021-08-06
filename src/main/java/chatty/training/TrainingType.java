package chatty.training;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TrainingType {
    SVM,
    CNN,
    QA_CNN,
    LSTM;


    @JsonValue
    public String getValue() {
        return this.name();
    }
}
