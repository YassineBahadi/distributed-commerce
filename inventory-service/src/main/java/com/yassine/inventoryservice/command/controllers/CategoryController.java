package com.yassine.inventoryservice.command.controllers;

import com.yassine.inventoryservice.command.commands.CreateCategoryCommand;
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
@RequestMapping("/categories")
public class CategoryController {

    private final CommandGateway commandGateway;

    public CategoryController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createCategory(
            @RequestBody CreateCategoryRequest request) {

        String id = UUID.randomUUID().toString();

        return commandGateway.send(new CreateCategoryCommand(
                id,
                request.name(),
                request.description()
        ));
    }

    public record CreateCategoryRequest(
            String name,
            String description
    ) {}
}