package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    @Query("select order from OrderEntity order where order.orderTime<= :start and order.orderTime>=:end")
    List<OrderEntity> getOrderEntitiesForLastDay(LocalDateTime start, LocalDateTime end);
}
