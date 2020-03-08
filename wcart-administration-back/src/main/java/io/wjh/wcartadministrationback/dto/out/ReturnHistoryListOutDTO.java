package io.wjh.wcartadministrationback.dto.out;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ReturnHistoryListOutDTO {
    private Long returnHistoryId;
    private Long timestamp;
    private Byte returnStatus;
    private String comment;
    private Boolean customerNotify;

    public Long getReturnHistoryId() {
        return returnHistoryId;
    }

    public void setReturnHistoryId(Long returnHistoryId) {
        this.returnHistoryId = returnHistoryId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Byte getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Byte returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCustomerNotify() {
        return customerNotify;
    }

    public void setCustomerNotify(Boolean customerNotify) {
        this.customerNotify = customerNotify;
    }
}
