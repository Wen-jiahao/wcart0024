package io.wjh.wcartstoreback.dto.in;

public class ProductSearchInDTO {
    private String productName;
    private String profuctCode;
    private Double price;
    private Integer quantity;
    private Byte status;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProfuctCode() {
        return profuctCode;
    }

    public void setProfuctCode(String profuctCode) {
        this.profuctCode = profuctCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
