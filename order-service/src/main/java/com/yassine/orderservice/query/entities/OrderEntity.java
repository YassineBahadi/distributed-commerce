package com.yassine.orderservice.query.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pc
 **/
@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    private String id;

    private String customerId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String deliveryAddress;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderLineEntity> lines = new ArrayList<>();
}