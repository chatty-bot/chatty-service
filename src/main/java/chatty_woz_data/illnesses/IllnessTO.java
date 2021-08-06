package chatty_woz_data.illnesses;

import java.util.List;
import chatty_woz_data.symptoms.SymptomTO;

public class IllnessTO {


    private Integer id;
    private String illnessName;
    private List<SymptomTO> symptoms;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getIllnessName() {
        return illnessName;
    }


    public void setIllnessName(final String illnessName) {
        this.illnessName = illnessName;
    }


    public List<SymptomTO> getSymptoms() {
        return symptoms;
    }


    public void setSymptoms(final List<SymptomTO> symptoms) {
        this.symptoms = symptoms;
    }
}
