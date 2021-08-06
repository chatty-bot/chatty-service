package chatty.metrics;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import chatty.training.TrainingType;

public class MetricsTO {


    private Integer id;


    private Integer classifierId;


    private Integer intentId;


    @NotBlank
    private String intentName;

    @NotNull
    private TrainingType trainingType;


    @NotBlank
    private float precision;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getClassifierId() {
        return classifierId;
    }


    public void setClassifierId(final Integer classifierId) {
        this.classifierId = classifierId;
    }


    public Integer getIntentId() {
        return intentId;
    }


    public void setIntentId(final Integer intentId) {
        this.intentId = intentId;
    }


    public String getIntentName() {
        return intentName;
    }


    public void setIntentName(final String intentName) {
        this.intentName = intentName;
    }


    public TrainingType getTrainingType() {
        return trainingType;
    }


    public void setTrainingType(final TrainingType trainingType) {
        this.trainingType = trainingType;
    }


    public float getPrecision() {
        return precision;
    }


    public void setPrecision(final float precision) {
        this.precision = precision;
    }
}
