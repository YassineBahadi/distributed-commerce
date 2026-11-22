package com.yassine.inventoryservice.query.repositories;

import com.yassine.inventoryservice.query.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface CategoryRepository
        extends JpaRepository<CategoryEntity, String> {
}