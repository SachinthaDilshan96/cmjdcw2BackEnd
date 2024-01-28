package com.ijse.cmjddw2.service.impl;

import com.ijse.cmjddw2.dto.requestDto.OrderRequestDto;
import com.ijse.cmjddw2.dto.requestDto.ProductRequestDto;
import com.ijse.cmjddw2.dto.responseDto.OrderResponseDto;
import com.ijse.cmjddw2.entity.OrderEntity;
import com.ijse.cmjddw2.entity.ProductEntity;
import com.ijse.cmjddw2.repository.OrderRepository;
import com.ijse.cmjddw2.repository.ProductRepository;
import com.ijse.cmjddw2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        OrderEntity order = new OrderEntity();
        List<Integer> products = orderRequestDto.getProducts();
        Set<ProductEntity> productEntitySet = new HashSet<>();
        order.setTotal(0.0);
        for (Integer productId:products){
            ProductEntity product = productRepository.findById(productId).orElse(null);
            if (product != null && product.getQty() != 0){
                productEntitySet.add(product);
                order.setTotal(order.getTotal()+product.getUnitPrice());
                product.setQty(product.getQty()-1);
                productRepository.save(product);
            }
        }
        order.setProducts(productEntitySet);
        order.setOrderTime(LocalDateTime.now());
        OrderEntity savedOrder = orderRepository.save(order);
        if (savedOrder != null){
            OrderResponseDto responseDto = new OrderResponseDto();
            responseDto.setId(savedOrder.getOrderId());
            return responseDto;
        }
        return null;
    }

    @Override
    public OrderEntity getOrderByID(int id) {
        return null;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return null;
    }
}
