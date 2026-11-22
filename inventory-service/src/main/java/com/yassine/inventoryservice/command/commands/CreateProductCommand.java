package com.yassine.inventoryservice.command.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author pc
 **/
public record CreateProductCommand(
        @TargetAggregateIdentifier String productId,
        String name,
        double price,
        int quantity,
        String categoryId
) {}