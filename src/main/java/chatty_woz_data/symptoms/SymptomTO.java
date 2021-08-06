package chatty_woz_data.symptoms;

public class SymptomTO {


    private Integer id;
    private String symptomName;
    private String symptomFrequency;
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
