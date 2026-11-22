package com.yassine.orderservice.query.controllers;

import com.yassine.orderservice.query.entities.OrderEntity;
import com.yassine.orderservice.query.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/query/orders")
public class OrderQueryController {

    private final OrderRepository repository;

    public OrderQueryController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<OrderEntity> getOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public OrderEntity getOrder(@PathVariable String id) {
        return repository.findById(id).orElseThrow();
    }
}