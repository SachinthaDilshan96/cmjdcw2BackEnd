package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.CategoryEntity;
import com.ijse.cmjddw2.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    @Query("SELECT p from ProductEntity p where p.category=:category")
    List<ProductEntity> findProductEntityByCategory(@Param("category")CategoryEntity category);

    @Query("select p from ProductEntity p where p.qty<=1")
    List<ProductEntity> getLowStockProducts();

    @Query("select p from ProductEntity p where p.expireDate=:today")
    List<ProductEntity> getTodayExpiringProducts(long today);




}
