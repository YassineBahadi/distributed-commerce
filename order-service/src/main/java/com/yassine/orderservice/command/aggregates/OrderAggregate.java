package com.yassine.orderservice.command.aggregates;

import com.yassine.orderservice.command.commands.CreateOrderCommand;
import com.yassine.orderservice.command.events.OrderCreatedEvent;
import com.yassine.orderservice.command.events.OrderLineData;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author pc
 **/

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private String customerId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String deliveryAddress;
    private OrderStatus status;
    private List<OrderLineData> lines;

    protected OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {

        apply(new OrderCreatedEvent(
                command.orderId(),
                command.customerId(),
                LocalDateTime.now(),
                command.deliveryDate(),
                command.deliveryAddress(),
                OrderStatus.CREATED.name(),
                command.lines()
        ));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {

        this.orderId = event.getOrderId();
        this.customerId = event.getCustomerId();
        this.orderDate = event.getOrderDate();
        this.deliveryDate = event.getDeliveryDate();
        this.deliveryAddress = event.getDeliveryAddress();
        this.status = OrderStatus.valueOf(event.getStatus());
        this.lines = event.getLines();
    }
}