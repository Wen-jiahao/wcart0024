package io.wjh.wcartadministrationback.dto.in;

import org.omg.CORBA.PRIVATE_MEMBER;

public class OrderHistoryCreateInDTO {
    private Long orderId;
    private Byte orderStatus;
    private String comment;
    private Boolean customeNotified;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCustomeNotified() {
        return customeNotified;
    }

    public void setCustomeNotified(Boolean customeNotified) {
        this.customeNotified = customeNotified;
    }
}
