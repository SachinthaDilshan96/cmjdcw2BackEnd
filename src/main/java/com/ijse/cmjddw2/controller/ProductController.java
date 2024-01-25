package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.ProductRequestDto;
import com.ijse.cmjddw2.dto.responseDto.ProductResponseDto;
import com.ijse.cmjddw2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.saveProduct(productRequestDto);
        if (productResponseDto != null){
            return ResponseEntity.ok().body(productRequestDto);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/category/{id}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable int catID){
        List<ProductResponseDto> responseDtos = productService.getProductsByCategory(catID);
        if (responseDtos != null){
            return ResponseEntity.ok().body(responseDtos);
        }
        return ResponseEntity.noContent().build();
    }
}
