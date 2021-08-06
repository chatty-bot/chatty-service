package chatty.answer_selection.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import chatty.classifier.types.ClassifierTO;

/**
 * I'm the transport object to deliver information of an answer selection module {@link AnswerSelectionEntity} to the frontend
 */
public class AnswerSelectionTO {


    private Integer id;
    private Integer userId;
    @NotBlank
    private String answerSelectionName;
    @NotNull
    private List<ClassifierTO> classifiers = new ArrayList<>();


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }


    public String getAnswerSelectionName() {
        return answerSelectionName;
    }


    public void setAnswerSelectionName(final String answerSelectionName) {
        this.answerSelectionName = answerSelectionName;
    }


    public List<ClassifierTO> getClassifiers() {
        return classifiers;
    }


    public void setClassifiers(final List<ClassifierTO> classifiers) {
        this.classifiers = classifiers;
    }
}
