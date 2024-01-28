package com.ijse.cmjddw2.service;

import com.ijse.cmjddw2.dto.requestDto.OrderRequestDto;
import com.ijse.cmjddw2.dto.responseDto.OrderResponseDto;
import com.ijse.cmjddw2.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderEntity getOrderByID(int id);
    List<OrderEntity> getAllOrders();
}
