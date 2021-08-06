package chatty.answer_selection.types;

import javax.validation.constraints.NotBlank;

/**
 * Transport object for answer selection modules
 */
public class AnswerSelectionDataSampleTO {


    private Integer userId;
    private Integer answerSelectionId;


    @NotBlank
    private String text;

    @NotBlank
    private String response;

    @NotBlank
    private String intentName;


    public String getText() {
        return text;
    }


    public void setText(final String text) {
        this.text = text;
    }


    public String getResponse() {
        return response;
    }


    public void setResponse(final String response) {
        this.response = response;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }


    public Integer getAnswerSelectionId() {
        return answerSelectionId;
    }


    public void setAnswerSelectionId(final Integer answerSelectionId) {
        this.answerSelectionId = answerSelectionId;
    }


    public String getIntentName() {
        return intentName;
    }


    public void setIntentName(final String intentName) {
        this.intentName = intentName;
    }
}


