package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.CategoryRequestDto;
import com.ijse.cmjddw2.dto.responseDto.CategoryResponseDto;
import com.ijse.cmjddw2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getLastID")
    public ResponseEntity<?> getLastId(){
        return ResponseEntity.ok().body(categoryService.getNextCategoryId());
    }

    @PostMapping("/category/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDto createCategoryDto){
        return ResponseEntity.ok().body(categoryService.createCategory(createCategoryDto));
    }

    @GetMapping("/category/getAllCategories")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryResponseDto> responseDtos = categoryService.getAllCategories();
        if (responseDtos != null){
            return ResponseEntity.ok().body(responseDtos);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryByID(@PathVariable int id){
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        if (categoryResponseDto != null){
            return ResponseEntity.ok().body(categoryResponseDto);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryRequestDto,id);
        if (categoryResponseDto != null){
            return ResponseEntity.ok().body(categoryRequestDto);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
