package com.yassine.inventoryservice.command.aggregates;

import com.yassine.inventoryservice.command.commands.CreateProductCommand;
import com.yassine.inventoryservice.command.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author pc
 **/
@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private String name;
    private double price;
    private int quantity;
    private String categoryId;
    private ProductStatus status;

    protected ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        apply(new ProductCreatedEvent(
                command.productId(),
                command.name(),
                command.price(),
                command.quantity(),
                command.categoryId()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.productId();
        this.name = event.name();
        this.price = event.price();
        this.quantity = event.quantity();
        this.categoryId = event.categoryId();

        this.status = event.quantity() > 0
                ? ProductStatus.AVAILABLE
                : ProductStatus.OUT_OF_STOCK;
    }
}