package chatty.dst.types;

public class DstTO {


    private Integer id;
    private boolean train;
    private String fileName;
    private String dstName;
    private Integer userId;


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


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
}
