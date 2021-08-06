package chatty.metrics;
// TODO

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import chatty.training.TrainingType;

@Table(name = "t_metrics")
@Entity
public class MetricsEntity {


    @Id
    @GeneratedValue(generator = "g_metrics", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_metrics", sequenceName = "sq_metrics", allocationSize = 1)
    private Integer id;


    @Column(name = "classifier_id")
    private Integer classifierId;


    @Column(name = "intent_id")
    private Integer intentId;


    @Column(name = "intent_name")
    private String intentName;

    @Column(name = "training_type")
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;


    @Column
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


    public float getPrecision() {
        return precision;
    }


    public void setPrecision(final float precision) {
        this.precision = precision;
    }


    public TrainingType getTrainingType() {
        return trainingType;
    }


    public void setTrainingType(final TrainingType trainingType) {
        this.trainingType = trainingType;
    }
}

