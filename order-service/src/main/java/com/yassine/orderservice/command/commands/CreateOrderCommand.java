package com.yassine.orderservice.command.commands;

import com.yassine.orderservice.command.events.OrderLineData;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author pc
 **/
public record CreateOrderCommand(
        @TargetAggregateIdentifier String orderId,
        String customerId,
        LocalDateTime deliveryDate,
        String deliveryAddress,
        List<OrderLineData> lines
) {}
