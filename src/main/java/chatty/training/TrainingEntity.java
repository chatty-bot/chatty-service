package chatty.training;


import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_trainings")
public class TrainingEntity {

    @Id
    @GeneratedValue(generator = "g_trainings", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_trainings", sequenceName = "sq_trainings", allocationSize = 1)
    private Integer id;


    @Column(name = "classifier_id")
    private Integer classifierId;

    @Column(name = "answer_selection_id")
    private Integer answerSelectionId;


    @Column(name = "training_type")
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;


    @Column(name = "training_status")
    @Enumerated(EnumType.STRING)
    private TrainingStatus trainingStatus;


    @Column(name = "started_at")
    private OffsetDateTime startedAt;


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


    public TrainingType getTrainingType() {
        return trainingType;
    }


    public void setTrainingType(final TrainingType trainingType) {
        this.trainingType = trainingType;
    }


    public TrainingStatus getTrainingStatus() {
        return trainingStatus;
    }


    public void setTrainingStatus(final TrainingStatus trainingStatus) {
        this.trainingStatus = trainingStatus;
    }


    public OffsetDateTime getStartedAt() {
        return startedAt;
    }


    public void setStartedAt(final OffsetDateTime startedAt) {
        this.startedAt = startedAt;
    }


    public Integer getAnswerSelectionId() {
        return answerSelectionId;
    }


    public void setAnswerSelectionId(final Integer answerSelectionId) {
        this.answerSelectionId = answerSelectionId;
    }
}
