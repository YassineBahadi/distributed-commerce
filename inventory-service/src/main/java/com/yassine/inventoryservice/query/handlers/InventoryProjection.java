package com.yassine.inventoryservice.query.handlers;

import com.yassine.inventoryservice.command.events.CategoryCreatedEvent;
import com.yassine.inventoryservice.command.events.ProductCreatedEvent;
import com.yassine.inventoryservice.query.entities.CategoryEntity;
import com.yassine.inventoryservice.query.entities.ProductEntity;
import com.yassine.inventoryservice.query.repositories.CategoryRepository;
import com.yassine.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author pc
 **/
@Component
public class InventoryProjection {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public InventoryProjection(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        CategoryEntity category = new CategoryEntity();
        category.setId(event.getCategoryId());
        category.setName(event.getName());
        category.setDescription(event.getDescription());

        categoryRepository.save(category);
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity product = new ProductEntity();
        product.setId(event.getProductId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        product.setCategoryId(event.getCategoryId());
        product.setStatus(
                event.getQuantity() > 0 ? "AVAILABLE" : "OUT_OF_STOCK"
        );

        productRepository.save(product);
    }
}