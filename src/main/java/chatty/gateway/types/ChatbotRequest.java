package chatty.gateway.types;

import java.util.List;
import java.util.Map;

public class ChatbotRequest {
    private String message;
    private List<String> systemActs;
    private Map<String, Map<String, String>> beliefState;
    private Integer txtClfId;
    private Integer dstId;
    private Integer seq2SeqId;
    private Integer answSelId;


    public String getMessage() {
        return message;
    }


    public void setMessage(final String message) {
        this.message = message;
    }


    public List<String> getSystemActs() {
        return systemActs;
    }


    public void setSystemActs(final List<String> systemActs) {
        this.systemActs = systemActs;
    }


    public Map<String, Map<String, String>> getBeliefState() {
        return beliefState;
    }


    public void setBeliefState(final Map<String, Map<String, String>> beliefState) {
        this.beliefState = beliefState;
    }


    public Integer getTxtClfId() {
        return txtClfId;
    }


    public void setTxtClfId(final Integer txtClfId) {
        this.txtClfId = txtClfId;
    }


    public Integer getDstId() {
        return dstId;
    }


    public void setDstId(final Integer dstId) {
        this.dstId = dstId;
    }


    public Integer getSeq2SeqId() {
        return seq2SeqId;
    }


    public void setSeq2SeqId(final Integer seq2SeqId) {
        this.seq2SeqId = seq2SeqId;
    }


    public Integer getAnswSelId() {
        return answSelId;
    }


    public void setAnswSelId(final Integer answSelId) {
        this.answSelId = answSelId;
    }
}
