package com.ijse.cmjddw2.service;

import com.ijse.cmjddw2.dto.requestDto.OrderRequestDto;
import com.ijse.cmjddw2.dto.responseDto.DailySalesResponseDto;
import com.ijse.cmjddw2.dto.responseDto.OrderResponseDto;
import com.ijse.cmjddw2.dto.responseDto.StatResponseDto;
import com.ijse.cmjddw2.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderEntity getOrderByID(int id);
    List<OrderEntity> getAllOrders();
    StatResponseDto getOrdersBetween(LocalDateTime start, LocalDateTime end);
    List<DailySalesResponseDto> getOrdersForLastWeek(LocalDateTime start, LocalDateTime end);
}
