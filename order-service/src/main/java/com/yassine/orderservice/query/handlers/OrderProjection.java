package com.yassine.orderservice.query.handlers;

import com.yassine.orderservice.command.events.OrderCreatedEvent;
import com.yassine.orderservice.command.events.OrderLineData;
import com.yassine.orderservice.query.entities.OrderEntity;
import com.yassine.orderservice.query.entities.OrderLineEntity;
import com.yassine.orderservice.query.repositories.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author pc
 **/
@Component
public class OrderProjection {

    private final OrderRepository repository;

    public OrderProjection(OrderRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {

        OrderEntity order = new OrderEntity();

        order.setId(event.getOrderId());
        order.setCustomerId(event.getCustomerId());
        order.setOrderDate(event.getOrderDate());
        order.setDeliveryDate(event.getDeliveryDate());
        order.setDeliveryAddress(event.getDeliveryAddress());
        order.setStatus(event.getStatus());

        for (OrderLineData data : event.getLines()) {

            OrderLineEntity line = new OrderLineEntity();

            line.setProductId(data.getProductId());
            line.setQuantity(data.getQuantity());
            line.setUnitPrice(data.getUnitPrice());
            line.setDiscount(data.getDiscount());
            line.setOrder(order);

            order.getLines().add(line);
        }

        repository.save(order);
    }
}