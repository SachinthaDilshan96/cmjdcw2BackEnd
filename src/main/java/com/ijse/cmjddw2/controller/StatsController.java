package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.StatRequestDto;
import com.ijse.cmjddw2.dto.responseDto.StatResponseDto;
import com.ijse.cmjddw2.entity.OrderEntity;
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
public class StatsController {
    @Autowired
    OrderService orderService;
    @PostMapping("/stats")
    public ResponseEntity<?> getDashboardStats(@RequestBody StatRequestDto statRequestDto){
        StatResponseDto statResponseDto = orderService.getOrdersBetween(statRequestDto.getCurrentDate().toLocalDateTime(),statRequestDto.getCurrentDate().toLocalDateTime().minusHours(24));
        return ResponseEntity.ok().body(statResponseDto);
    }
}
