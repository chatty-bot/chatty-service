package chatty_woz_data.symptoms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_symptoms")
public class SymptomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "symptom_name")
    private String symptomName;

    @Column(name = "symptom_frequency")
    private String symptomFrequency;


    @Column(name = "illness_name")
    private String illnessName;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getSymptomName() {
        return symptomName;
    }


    public void setSymptomName(final String symptomName) {
        this.symptomName = symptomName;
    }


    public String getSymptomFrequency() {
        return symptomFrequency;
    }


    public void setSymptomFrequency(final String symptomFrequency) {
        this.symptomFrequency = symptomFrequency;
    }


    public String getIllnessName() {
        return illnessName;
    }


    public void setIllnessName(final String illnessName) {
        this.illnessName = illnessName;
    }
}
