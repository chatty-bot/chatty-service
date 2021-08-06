package chatty.classifier.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import chatty.intents.IntentTO;

/**
 * I'm the frontend object for a particular classifier without related data entities.
 */
public class ClassifierTO {

    private Integer userId;
    private Integer id;

    @NotBlank
    private String classifierName;


    @NotNull
    private List<IntentTO> intents = new ArrayList<>();


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getClassifierName() {
        return classifierName;
    }


    public void setClassifierName(final String classifierName) {
        this.classifierName = classifierName;
    }


    public List<IntentTO> getIntents() {
        return intents;
    }


    public void setIntents(final List<IntentTO> intents) {
        this.intents = intents;
    }
}
