package io.wjh.wcartadministrationback.dto.out;

public class OrderHistoryListOutDTO {

    private Long orderHistoryId;
    private Long timestamp;
    private String comment;
    private Byte orderStatus;
    private Boolean customeNotified;

    public Long getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getCustomeNotified() {
        return customeNotified;
    }

    public void setCustomeNotified(Boolean customeNotified) {
        this.customeNotified = customeNotified;
    }
}
