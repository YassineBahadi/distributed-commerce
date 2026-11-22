package com.yassine.inventoryservice.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pc
 **/
@Entity
@Getter
@Setter
public class ProductEntity {

    @Id
    private String id;

    private String name;
    private double price;
    private int quantity;
    private String status;
    private String categoryId;
}
