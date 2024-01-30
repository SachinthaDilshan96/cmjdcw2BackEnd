package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.OrderRequestDto;
import com.ijse.cmjddw2.dto.requestDto.StatRequestDto;
import com.ijse.cmjddw2.dto.responseDto.DailySalesResponseDto;
import com.ijse.cmjddw2.dto.responseDto.OrderResponseDto;
import com.ijse.cmjddw2.dto.responseDto.StatResponseDto;
import com.ijse.cmjddw2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);
        if (orderRequestDto != null){
            return ResponseEntity.ok().body(orderResponseDto);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/orders/sevenDays")
    public ResponseEntity<?> getOrders(@RequestBody StatRequestDto statRequestDto){
        List<DailySalesResponseDto> dailySalesResponseDtos = orderService.getOrdersForLastWeek(statRequestDto.getCurrentDate().toLocalDateTime(),statRequestDto.getCurrentDate().toLocalDateTime().minusHours(168));
        if (dailySalesResponseDtos != null){
            return ResponseEntity.ok().body(dailySalesResponseDtos);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
