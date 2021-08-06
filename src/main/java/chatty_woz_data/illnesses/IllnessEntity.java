package chatty_woz_data.illnesses;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_illness")
public class IllnessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "illness_name")
    private String illnessName;


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


}
