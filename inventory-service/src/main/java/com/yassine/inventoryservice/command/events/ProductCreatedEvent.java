package com.yassine.inventoryservice.command.events;

/**
 * @author pc
 **/
public class ProductCreatedEvent {

    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String categoryId;

    // Constructeur vide pour XStream
    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(
            String productId,
            String name,
            double price,
            int quantity,
            String categoryId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}