package chatty.user;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import chatty.answer_selection.types.AnswerSelectionEntity;
import chatty.classifier.types.ClassifierEntity;
import chatty.seq2seq.Seq2SeqEntity;
import io.micronaut.security.authentication.providers.UserState;


@Entity
@Table(name = "t_users")
public class UserEntity implements UserState {

    @Id
    @GeneratedValue(generator = "g_users", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "g_users", sequenceName = "sq_users", allocationSize = 1)
    private int id;

    @Column(name = "user_name")
    private String username;


    private String password;
    private String email;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    public List<ClassifierEntity> classifiers = new ArrayList<>();
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    public List<AnswerSelectionEntity> answerSelections = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    public List<Seq2SeqEntity> seq2SeqEntities = new ArrayList<>();

    @Transient
    private boolean enabled = true;
    @Transient
    private boolean accountExpired = false;
    @Transient
    private boolean accountLocked = false;
    @Transient
    private boolean passwordExpired = false;


    public int getId() {
        return id;
    }


    public void setId(final int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(final String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public boolean isAccountExpired() {
        return accountExpired;
    }


    @Override
    public boolean isAccountLocked() {
        return accountLocked;
    }


    @Override
    public boolean isPasswordExpired() {
        return passwordExpired;
    }


    public void setPassword(final String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(final String email) {
        this.email = email;
    }


    public List<ClassifierEntity> getClassifiers() {
        return classifiers;
    }


    public void setClassifiers(final List<ClassifierEntity> classifiers) {
        this.classifiers = classifiers;
    }


    public List<AnswerSelectionEntity> getAnswerSelections() {
        return answerSelections;
    }


    public void setAnswerSelections(final List<AnswerSelectionEntity> answerSelections) {
        this.answerSelections = answerSelections;
    }


    public List<Seq2SeqEntity> getSeq2SeqEntities() {
        return seq2SeqEntities;
    }


    public void setSeq2SeqEntities(final List<Seq2SeqEntity> seq2SeqEntities) {
        this.seq2SeqEntities = seq2SeqEntities;
    }
}
