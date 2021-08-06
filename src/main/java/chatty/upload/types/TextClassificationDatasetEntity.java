package chatty.upload.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_txt_clf_datasets")
public class TextClassificationDatasetEntity {

    @Id
    @GeneratedValue(generator = "g_datasets", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_datasets", sequenceName = "sq_txt_clf_datasets", allocationSize = 1)
    private Integer id;


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "number_of_samples")
    private int numberOfSamples;

    @Column(name = "classifier_id")
    private Integer classifierId;


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


    public String getFileName() {
        return fileName;
    }


    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }


    public int getNumberOfSamples() {
        return numberOfSamples;
    }


    public void setNumberOfSamples(final int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }


}
