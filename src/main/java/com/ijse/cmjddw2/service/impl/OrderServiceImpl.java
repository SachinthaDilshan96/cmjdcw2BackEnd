package com.ijse.cmjddw2.service.impl;

import com.ijse.cmjddw2.dto.requestDto.OrderRequestDto;
import com.ijse.cmjddw2.dto.responseDto.DailySalesResponseDto;
import com.ijse.cmjddw2.dto.responseDto.OrderResponseDto;
import com.ijse.cmjddw2.dto.responseDto.StatResponseDto;
import com.ijse.cmjddw2.entity.OrderEntity;
import com.ijse.cmjddw2.entity.ProductEntity;
import com.ijse.cmjddw2.repository.OrderRepository;
import com.ijse.cmjddw2.repository.ProductRepository;
import com.ijse.cmjddw2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public StatResponseDto getOrdersBetween(LocalDateTime start, LocalDateTime end) {
        List<OrderEntity>  orderEntities =  orderRepository.getOrderEntitiesForThePeriod(start,end);
        StatResponseDto statResponseDto = new StatResponseDto();
        statResponseDto.setLastDayOrderCount(orderEntities.size());
        double total = 0;
        for (OrderEntity order:orderEntities){
            total+=order.getTotal();
        }
        statResponseDto.setLastDayTotalSales(total);
        List<ProductEntity> productEntities = productRepository.getLowStockProducts();
        statResponseDto.setTotalLowStocks(productEntities.size());
        List<ProductEntity> todayExpiringProducts = productRepository.getTodayExpiringProducts(Timestamp.valueOf(start).getTime());
        statResponseDto.setTodayExpires(todayExpiringProducts.size());
        long totalProducts = productRepository.count();
        statResponseDto.setTotalProducts(totalProducts);
        return statResponseDto;
    }

    @Override
    public List<DailySalesResponseDto> getOrdersForLastWeek(LocalDateTime start, LocalDateTime end) {
        List<Object[]>  orderEntities =  orderRepository.getOrderEntitiesForLastWeek(start,end);
        List<DailySalesResponseDto> responseDtos = new ArrayList<>();
        for (Object[] order:orderEntities){
            DailySalesResponseDto salesResponseDto =  new DailySalesResponseDto();
            salesResponseDto.setDate(Timestamp.valueOf((LocalDateTime) order[0]).getTime());
            salesResponseDto.setSales((Double) order[1]);
            responseDtos.add(salesResponseDto);
        }
        return responseDtos;
    }
}
