package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    @Query("select order from OrderEntity order where order.orderTime<= :start and order.orderTime>=:end")
    List<OrderEntity> getOrderEntitiesForThePeriod(LocalDateTime start, LocalDateTime end);
    //@Query("SELECT c.year, COUNT(c.year) FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")

    @Query("select order.orderTime,sum(order.total) from OrderEntity as order where order.orderTime<= :start and order.orderTime>=:end group by order.orderTime")
    List<Object[]> getOrderEntitiesForLastWeek(LocalDateTime start, LocalDateTime end);

}
