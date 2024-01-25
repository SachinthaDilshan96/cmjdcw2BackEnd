package com.ijse.cmjddw2.service;

import com.ijse.cmjddw2.dto.requestDto.CategoryRequestDto;
import com.ijse.cmjddw2.dto.responseDto.CategoryResponseDto;
import com.ijse.cmjddw2.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    int getNextCategoryId();
    CategoryResponseDto getCategoryById(int id);
    CategoryEntity createCategory(CategoryRequestDto createCategoryDto);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto updateCategory(CategoryRequestDto categoryDto,int id);
}
