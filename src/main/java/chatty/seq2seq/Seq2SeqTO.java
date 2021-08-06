package chatty.seq2seq;

public class Seq2SeqTO {

    private Integer id;
    private String fileName;
    private Boolean train;
    private Integer userId;
    private String seq2SeqName;


    public Integer getId() {
        return id;
    }


    public void setId(final Integer id) {
        this.id = id;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }


    public Boolean getTrain() {
        return train;
    }


    public void setTrain(final Boolean train) {
        this.train = train;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }


    public String getSeq2SeqName() {
        return seq2SeqName;
    }


    public void setSeq2SeqName(final String seq2SeqName) {
        this.seq2SeqName = seq2SeqName;
    }
}
