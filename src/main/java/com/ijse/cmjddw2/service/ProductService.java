package com.ijse.cmjddw2.service;

import com.ijse.cmjddw2.dto.requestDto.ProductRequestDto;
import com.ijse.cmjddw2.dto.responseDto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductResponseDto> getProductsByCategory(int id);
    ProductResponseDto saveProduct(ProductRequestDto productRequestDto);
}
