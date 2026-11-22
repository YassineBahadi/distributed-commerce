package com.yassine.inventoryservice.query.controllers;

import com.yassine.inventoryservice.query.entities.ProductEntity;
import com.yassine.inventoryservice.query.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/query/products")
public class ProductQueryController {

    private final ProductRepository repository;

    public ProductQueryController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity getProduct(@PathVariable String id) {
        return repository.findById(id).orElseThrow();
    }
}
