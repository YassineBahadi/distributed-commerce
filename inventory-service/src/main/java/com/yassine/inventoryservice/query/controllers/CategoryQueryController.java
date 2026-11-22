package com.yassine.inventoryservice.query.controllers;

import com.yassine.inventoryservice.query.entities.CategoryEntity;
import com.yassine.inventoryservice.query.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/query/categories")
public class CategoryQueryController {

    private final CategoryRepository repository;

    public CategoryQueryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CategoryEntity> getCategories() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CategoryEntity getCategory(@PathVariable String id) {
        return repository.findById(id).orElseThrow();
    }
}