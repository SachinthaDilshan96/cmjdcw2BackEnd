package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    Boolean existsByName(String name);
    @Query("SELECT LAST_INSERT_ID()")
    int getLastID();

}
