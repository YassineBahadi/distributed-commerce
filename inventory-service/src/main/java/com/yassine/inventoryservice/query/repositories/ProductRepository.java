package com.yassine.inventoryservice.query.repositories;

import com.yassine.inventoryservice.query.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface ProductRepository
        extends JpaRepository<ProductEntity, String> {
}