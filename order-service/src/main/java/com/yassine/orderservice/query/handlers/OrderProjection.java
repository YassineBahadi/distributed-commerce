package com.yassine.orderservice.query.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yassine.orderservice.command.events.OrderCreatedEvent;
import com.yassine.orderservice.command.events.OrderLineData;
import com.yassine.orderservice.query.entities.OrderEntity;
import com.yassine.orderservice.query.entities.OrderLineEntity;
import com.yassine.orderservice.query.repositories.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author pc
 **/
@Component
public class OrderProjection {

    private final OrderRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProjection(
            OrderRepository repository,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
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

        // 1. Sauvegarder la commande dans MySQL
        repository.save(order);

        // 2. Publier l'événement dans Kafka
        try {
            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(
                    "orders",
                    event.getOrderId(),
                    json
            );

        } catch (Exception e) {
            throw new RuntimeException("Kafka publishing failed", e);
        }
    }
}