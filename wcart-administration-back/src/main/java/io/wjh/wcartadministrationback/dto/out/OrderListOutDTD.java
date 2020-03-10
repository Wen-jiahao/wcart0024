package io.wjh.wcartadministrationback.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class OrderListOutDTD {
    private Long orderId;
    private Integer customerId;
    private Byte status;
    private Double totalPrice;
    private Long createTimestamp;
    @JsonIgnore
    private Date createTime;
    private Long updateTimestamp;
    @JsonIgnore
    private Date updateTime;
    private String customerName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCreateTimestamp() {
        return this.createTime==null?null:this.createTime.getTime();
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTimestamp() {
        return this.updateTime==null?null:this.updateTime.getTime();
    }

    public void setUpdateTimestamp(Long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}