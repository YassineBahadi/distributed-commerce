package com.yassine.inventoryservice.command.events;

/**
 * @author pc
 **/
public record ProductCreatedEvent(
        String productId,
        String name,
        double price,
        int quantity,
        String categoryId
) {}