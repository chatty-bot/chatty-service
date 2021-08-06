package chatty.classifier.types;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import chatty.intents.IntentEntity;
import chatty.user.UserEntity;

@Entity
@Table(name = "t_classifiers")
public class ClassifierEntity {


    @Id
    @GeneratedValue(generator = "g_classifiers", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_classifiers", sequenceName = "sq_classifiers", allocationSize = 1)
    private Integer id;

    @Column(name = "classifier_name")
    private String classifierName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    @OneToMany(mappedBy = "classifierEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    List<IntentEntity> intents = new ArrayList<>();


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


    @JsonGetter("userId")
    public int getUserId() {
        return userEntity.getId();
    }


    public List<IntentEntity> getIntents() {
        return intents;
    }


    public void setIntents(final List<IntentEntity> intents) {
        this.intents = intents;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }


    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
