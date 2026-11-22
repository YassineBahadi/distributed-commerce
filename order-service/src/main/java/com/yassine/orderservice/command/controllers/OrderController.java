package com.yassine.orderservice.command.controllers;

import com.yassine.orderservice.command.commands.CreateOrderCommand;
import com.yassine.orderservice.command.events.OrderLineData;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<Object> createOrder(
            @RequestBody CreateOrderRequest request) {

        String id = UUID.randomUUID().toString();

        return commandGateway.send(new CreateOrderCommand(
                id,
                request.customerId(),
                request.deliveryDate(),
                request.deliveryAddress(),
                request.lines()
        ));
    }

    public record CreateOrderRequest(
            String customerId,
            LocalDateTime deliveryDate,
            String deliveryAddress,
            List<OrderLineData> lines
    ) {}
}