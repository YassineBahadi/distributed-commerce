package com.yassine.inventoryservice.command.controllers;

import com.yassine.inventoryservice.command.commands.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/products")
public class ProductController {

    private final CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createProduct(
            @RequestBody CreateProductRequest request) {

        String id = UUID.randomUUID().toString();

        return commandGateway.send(new CreateProductCommand(
                id,
                request.name(),
                request.price(),
                request.quantity(),
                request.categoryId()
        ));
    }

    public record CreateProductRequest(
            String name,
            double price,
            int quantity,
            String categoryId
    ) {}
}
