package chatty.intents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import chatty.classifier.types.ClassifierEntity;

@Entity
@Table(name = "t_intents")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "exists",
                query = "SELECT count(e)> 0 FROM t_intents as e WHERE e.intent_name=?"
        )
})
public class IntentEntity {

    public static final String EXISTS_QUERY = "exists";

    @Id
    @GeneratedValue(generator = "g_intents", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_intents", sequenceName = "sq_intents", allocationSize = 1)
    private Integer id;

    @Column(name = "intent_name")
    private String intentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classifier_id")
    @JsonIgnore
    private ClassifierEntity classifierEntity;

    @Column(name = "number_of_samples")
    private int numberOfSamples;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getIntentName() {
        return intentName;
    }


    public void setIntentName(final String intentName) {
        this.intentName = intentName;
    }


    public ClassifierEntity getClassifierEntity() {
        return classifierEntity;
    }


    public void setClassifierEntity(final ClassifierEntity classifierEntity) {
        this.classifierEntity = classifierEntity;
    }


    public int getNumberOfSamples() {
        return numberOfSamples;
    }


    public void setNumberOfSamples(final int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }


    public void incrementNumberOfSamples() {
        this.numberOfSamples++;
    }
}
