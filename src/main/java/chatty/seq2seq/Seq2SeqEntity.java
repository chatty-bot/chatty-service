package chatty.seq2seq;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import chatty.user.UserEntity;

@Table(name = "t_seq2seq")
@Entity
public class Seq2SeqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private boolean train;
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "seq2seq_name")
    private String seq2SeqName;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public boolean isTrain() {
        return train;
    }


    public void setTrain(final boolean train) {
        this.train = train;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }


    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public String getSeq2SeqName() {
        return seq2SeqName;
    }


    public void setSeq2SeqName(final String seq2SeqName) {
        this.seq2SeqName = seq2SeqName;
    }
}
