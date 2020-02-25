package io.wjh.wcartadministrationback.dto.in;

public class ReturnHistoryCreateInDTO {

    private Integer returnId;
    private Byte returnStatus;
    private Boolean customerNotify;
    private String comment;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Byte getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Byte returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Boolean getCustomerNotify() {
        return customerNotify;
    }

    public void setCustomerNotify(Boolean customerNotify) {
        this.customerNotify = customerNotify;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
