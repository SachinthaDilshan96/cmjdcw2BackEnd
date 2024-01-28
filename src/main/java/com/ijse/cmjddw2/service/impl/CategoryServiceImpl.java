package com.ijse.cmjddw2.service.impl;

import com.ijse.cmjddw2.dto.requestDto.CategoryRequestDto;
import com.ijse.cmjddw2.dto.responseDto.CategoryResponseDto;
import com.ijse.cmjddw2.entity.CategoryEntity;
import com.ijse.cmjddw2.repository.CategoryRepository;
import com.ijse.cmjddw2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public int getNextCategoryId() {
        int lastId = categoryRepository.getLastID();
        return ++lastId;
    }

    @Override
    public CategoryResponseDto getCategoryById(int id) {
        CategoryEntity category =  categoryRepository.findById(id).orElse(null);
        if (category != null){
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setCategoryName(category.getName());
            categoryResponseDto.setId(category.getId());
            categoryResponseDto.setAddedOn(category.getAddedOn());
            categoryResponseDto.setAddedBy(category.getAddedBy());
            return categoryResponseDto;
        }
        return null;
    }

    @Override
    public CategoryEntity createCategory(CategoryRequestDto dto) {
        boolean isCategoryNameExists = categoryRepository.existsByName(dto.getCategoryName());
        if (!isCategoryNameExists){
            CategoryEntity category = new CategoryEntity();
            category.setName(dto.getCategoryName());
            category.setAddedOn(new Date().getTime());
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryResponseDto> responseDtos = new ArrayList<>();
        if (categoryEntities!=null){
            for (CategoryEntity category:categoryEntities) {
                CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                categoryResponseDto.setId(category.getId());
                categoryResponseDto.setCategoryName(category.getName());
                categoryResponseDto.setAddedBy(category.getAddedBy());
                categoryResponseDto.setAddedOn(category.getAddedOn());
                responseDtos.add(categoryResponseDto);
            }
            return responseDtos;
        }
        return null;
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryDto,int id) {
        CategoryEntity category = new CategoryEntity();
        CategoryEntity savedRecord = categoryRepository.findById(id).orElse(null);
        if (savedRecord != null){
            category.setId(id);
            category.setName(categoryDto.getCategoryName());
            category.setAddedBy(savedRecord.getAddedBy());
            category.setAddedOn(savedRecord.getAddedOn());
            CategoryEntity updatedRecord = categoryRepository.save(category);
            if (updatedRecord != null){
                CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                categoryResponseDto.setId(updatedRecord.getId());
                categoryResponseDto.setCategoryName(updatedRecord.getName());
                categoryResponseDto.setAddedOn(updatedRecord.getAddedOn());
                categoryResponseDto.setAddedBy(updatedRecord.getAddedBy());
                return categoryResponseDto;
            }
        }
        return null;
    }
}
