package chatty.classifier.types;

import javax.validation.constraints.NotBlank;
import io.micronaut.core.annotation.Introspected;

/**
 * I'm the frontend object that corresponds to a particular database entity {@link chatty.upload.types.TextClassificationDataSampleEntity}
 */
@Introspected
public class ClassifierDataSampleTO {

    private Integer id;
    private Integer userId;
    private Integer classifierId;

    @NotBlank
    private String label;
    @NotBlank
    private String text;


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


    public Integer getClassifierId() {
        return classifierId;
    }


    public void setClassifierId(final Integer classifierId) {
        this.classifierId = classifierId;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(final String label) {
        this.label = label;
    }


    public String getText() {
        return text;
    }


    public void setText(final String text) {
        this.text = text;
    }
}
