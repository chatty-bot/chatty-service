package chatty_woz_data.dialogue.types;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_slots")
public class SlotEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private StateEntity stateEntity;

    @Column
    private String label;


    @Column
    private String value;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public StateEntity getStateEntity() {
        return stateEntity;
    }


    public void setStateEntity(final StateEntity stateEntity) {
        this.stateEntity = stateEntity;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(final String label) {
        this.label = label;
    }


    public String getValue() {
        return value;
    }


    public void setValue(final String value) {
        this.value = value;
    }
}
