package com.yassine.inventoryservice.command.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author pc
 **/
public record CreateCategoryCommand(
        @TargetAggregateIdentifier String categoryId,
        String name,
        String description
) {}