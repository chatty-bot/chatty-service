package chatty.upload.types;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_ans_sel_datasets")
public class AnswerSelectionDatasetEntity {
    @Id
    @GeneratedValue(generator = "g_ans_sel_datasets", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_ans_sel_datasets", sequenceName = "sq_ans_sel_datasets", allocationSize = 1)
    private Integer id;

    @Column(name = "answer_selection_id")
    private Integer answerSelectionId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "number_of_samples")
    private int numberOfSamples;


    public void setAnswerSelectionId(final Integer answerSelectionId) {
        this.answerSelectionId = answerSelectionId;
    }


    public Integer getAnswerSelectionId() {
        return answerSelectionId;
    }


    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }


    public void setNumberOfSamples(final int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }


    public int getNumberOfSamples() {
        return numberOfSamples;
    }


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }
}
