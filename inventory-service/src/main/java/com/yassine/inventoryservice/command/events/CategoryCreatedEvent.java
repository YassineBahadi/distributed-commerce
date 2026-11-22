package com.yassine.inventoryservice.command.events;

/**
 * @author pc
 **/
public record CategoryCreatedEvent(
        String categoryId,
        String name,
        String description
) {}