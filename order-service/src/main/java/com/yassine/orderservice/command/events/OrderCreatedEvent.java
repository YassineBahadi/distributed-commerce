package com.yassine.orderservice.command.events;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author pc
 **/
public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String deliveryAddress;
    private String status;
    private List<OrderLineData> lines;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(
            String orderId,
            String customerId,
            LocalDateTime orderDate,
            LocalDateTime deliveryDate,
            String deliveryAddress,
            String status,
            List<OrderLineData> lines) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.lines = lines;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getStatus() { return status; }
    public List<OrderLineData> getLines() { return lines; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public void setStatus(String status) { this.status = status; }
    public void setLines(List<OrderLineData> lines) { this.lines = lines; }
}
