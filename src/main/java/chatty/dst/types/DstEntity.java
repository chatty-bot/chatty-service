package chatty.dst.types;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import chatty.user.UserEntity;

@Entity
@Table(name = "t_dst")
public class DstEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private boolean train;
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "dst_name")
    private String dstName;


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


    public String getDstName() {
        return dstName;
    }


    public void setDstName(final String dstName) {
        this.dstName = dstName;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }


    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
