package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
}
