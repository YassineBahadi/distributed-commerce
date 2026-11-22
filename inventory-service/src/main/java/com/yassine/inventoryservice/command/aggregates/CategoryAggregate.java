package com.yassine.inventoryservice.command.aggregates;

import com.yassine.inventoryservice.command.commands.CreateCategoryCommand;
import com.yassine.inventoryservice.command.events.CategoryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author pc
 **/
@Aggregate
public class CategoryAggregate {

    @AggregateIdentifier
    private String categoryId;

    private String name;
    private String description;

    protected CategoryAggregate() {}

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        apply(new CategoryCreatedEvent(
                command.categoryId(),
                command.name(),
                command.description()
        ));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.categoryId = event.getCategoryId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}