package chatty.answer_selection.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import chatty.user.UserEntity;

@Entity
@Table(name = "t_answer_selections")
public class AnswerSelectionEntity {


    @Id
    @GeneratedValue(generator = "g_answer_selection", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "sq_answer_selections", allocationSize = 1, name = "g_answer_selection")
    public Integer id;

    @Column(name = "answer_selection_name")
    private String answerSelectionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getAnswerSelectionName() {
        return answerSelectionName;
    }


    public void setAnswerSelectionName(final String answerSelectionName) {
        this.answerSelectionName = answerSelectionName;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }


    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
