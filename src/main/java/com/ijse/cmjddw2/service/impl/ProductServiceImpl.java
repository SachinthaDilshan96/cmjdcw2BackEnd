package com.ijse.cmjddw2.service.impl;

import com.ijse.cmjddw2.dto.requestDto.ProductRequestDto;
import com.ijse.cmjddw2.dto.responseDto.ProductResponseDto;
import com.ijse.cmjddw2.entity.CategoryEntity;
import com.ijse.cmjddw2.entity.ProductEntity;
import com.ijse.cmjddw2.repository.CategoryRepository;
import com.ijse.cmjddw2.repository.ProductRepository;
import com.ijse.cmjddw2.service.CategoryService;
import com.ijse.cmjddw2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductResponseDto> getProductsByCategory(int id) {
        List<ProductEntity> productEntities = null;
        if (id == 0){
            productEntities = productRepository.findAll();
        }else{
            CategoryEntity category = categoryRepository.findById(id).orElse(null);
            if (category != null){
                productEntities = productRepository.findProductEntityByCategory(category);
            }
        }
        if (productEntities != null){
            List<ProductResponseDto> responseDtos = new ArrayList<>();
            for (ProductEntity product:productEntities){
                ProductResponseDto responseDto = new ProductResponseDto();
                responseDto.setId(product.getProductId());
                responseDto.setCategory(product.getCategory().getId());
                responseDto.setName(product.getName());
                responseDto.setQty(product.getQty());
                responseDto.setUnitPrice(product.getUnitPrice());
                responseDto.setExpireDate(product.getExpireDate());
                responseDtos.add(responseDto);
            }
            return responseDtos;
        }
        return null;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        CategoryEntity category = categoryRepository.findById(productRequestDto.getCategoryId()).orElse(null);
        if (productRequestDto != null && category != null){
            ProductEntity entityToSaved = new ProductEntity();
            entityToSaved.setName(productRequestDto.getName());
            entityToSaved.setCategory(category);
            entityToSaved.setInitialQty(productRequestDto.getQty());
            entityToSaved.setQty(productRequestDto.getQty());
            entityToSaved.setUnitPrice(productRequestDto.getUnitPrice());
            entityToSaved.setExpireDate(productRequestDto.getExpireDate().getTime());
            entityToSaved.setAddedOn(new Timestamp(System.currentTimeMillis()).getTime());
            ProductEntity product = productRepository.save(entityToSaved);
            if (product != null){
                ProductResponseDto productResponseDto = new ProductResponseDto();
                productResponseDto.setName(product.getName());
                productResponseDto.setCategory(product.getCategory().getId());
                productResponseDto.setId(product.getProductId());
                productResponseDto.setQty(product.getQty());
                productResponseDto.setUnitPrice(product.getUnitPrice());
                productResponseDto.setExpireDate(product.getExpireDate());
                return productResponseDto;
            }
        }
        return null;
    }

    @Override
    public ProductResponseDto getProduct(int id) {
        ProductEntity product  = productRepository.findById(id).orElse(null);
        if (product != null){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getProductId());
            productResponseDto.setName(product.getName());
            productResponseDto.setCategory(product.getProductId());
            productResponseDto.setQty(product.getQty());
            productResponseDto.setUnitPrice(product.getUnitPrice());
            productResponseDto.setExpireDate(product.getExpireDate());
            return productResponseDto;
        }
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(int id, ProductRequestDto productRequestDto) {
        CategoryEntity category = categoryRepository.findById(productRequestDto.getCategoryId()).orElse(null);
        if (productRequestDto != null && category != null) {
            ProductEntity entityToSaved = new ProductEntity();
            entityToSaved.setProductId(id);
            entityToSaved.setName(productRequestDto.getName());
            entityToSaved.setCategory(category);
            entityToSaved.setInitialQty(productRequestDto.getQty());
            entityToSaved.setQty(productRequestDto.getQty());
            entityToSaved.setUnitPrice(productRequestDto.getUnitPrice());
            entityToSaved.setExpireDate(productRequestDto.getExpireDate().getTime());
            entityToSaved.setAddedOn(new Timestamp(System.currentTimeMillis()).getTime());
            ProductEntity product = productRepository.save(entityToSaved);
            if (product != null) {
                ProductResponseDto productResponseDto = new ProductResponseDto();
                productResponseDto.setName(product.getName());
                productResponseDto.setCategory(product.getCategory().getId());
                productResponseDto.setId(product.getProductId());
                productResponseDto.setQty(product.getQty());
                productResponseDto.setUnitPrice(product.getUnitPrice());
                productResponseDto.setExpireDate(product.getExpireDate());
                return productResponseDto;
            }
        }
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductResponseDto> products = new ArrayList<>();
        for (ProductEntity product:productEntities){
            ProductResponseDto responseDto = new ProductResponseDto();
            responseDto.setId(product.getProductId());
            responseDto.setName(product.getName());
            responseDto.setCategory(product.getCategory().getId());
            responseDto.setQty(product.getQty());
            responseDto.setInitialQty(product.getInitialQty());
            responseDto.setExpireDate(product.getExpireDate());
            responseDto.setUnitPrice(product.getUnitPrice());
            products.add(responseDto);
        }
        return products;
    }
}
