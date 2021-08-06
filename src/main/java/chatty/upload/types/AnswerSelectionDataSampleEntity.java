package chatty.upload.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_ans_sel_data_samples")
public class AnswerSelectionDataSampleEntity {

    @Id
    @GeneratedValue(generator = "g_ans_sel_data_samples", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_ans_sel_data_samples", sequenceName = "sq_ans_sel_data_samples", allocationSize = 1)
    private Integer id;
    private String text;
    private String response;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "answer_selection_id")
    private Integer answerSelectionId;

    @Column(name = "intent_name")
    private String intentName;


    public void setText(final String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }


    public void setResponse(final String response) {
        this.response = response;
    }


    public String getResponse() {
        return response;
    }


    public void setUserId(final int userId) {

        this.userId = userId;
    }


    public int getUserId() {
        return userId;
    }


    public void setAnswerSelectionId(final Integer answerSelectionId) {
        this.answerSelectionId = answerSelectionId;
    }


    public Integer getAnswerSelectionId() {
        return answerSelectionId;
    }


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
}
