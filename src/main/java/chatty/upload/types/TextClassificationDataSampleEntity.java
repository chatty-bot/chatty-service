package chatty.upload.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_txt_clf_data_samples")
public class TextClassificationDataSampleEntity {

    @Id
    @GeneratedValue(generator = "g_data_samples", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_data_samples", sequenceName = "sq_txt_clf_data_samples", allocationSize = 1)
    private Integer id;

    @Column
    private String text;
    @Column
    private String label;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "classifier_id")
    private Integer classifierId;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }


    public void setClassifierId(final Integer classifierId) {
        this.classifierId = classifierId;
    }


    public String getText() {
        return text;
    }


    public void setText(final String text) {
        this.text = text;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(final String label) {
        this.label = label;
    }


    public Integer getUserId() {
        return userId;
    }


    public Integer getClassifierId() {
        return classifierId;
    }
}
