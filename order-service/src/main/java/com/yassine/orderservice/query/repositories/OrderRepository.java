package com.yassine.orderservice.query.repositories;

import com.yassine.orderservice.query.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface OrderRepository
        extends JpaRepository<OrderEntity, String> {
}
