package com.yassine.orderservice.command.events;

/**
 * @author pc
 **/
public class OrderLineData {

    private String productId;
    private int quantity;
    private double unitPrice;
    private double discount;

    public OrderLineData() {
    }

    public OrderLineData(String productId, int quantity,
                         double unitPrice, double discount) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
