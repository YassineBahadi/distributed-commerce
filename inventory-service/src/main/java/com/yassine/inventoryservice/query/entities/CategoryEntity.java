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
public class CategoryEntity {

    @Id
    private String id;

    private String name;
    private String description;
}