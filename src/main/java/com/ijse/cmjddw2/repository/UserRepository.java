package com.ijse.cmjddw2.repository;

import com.ijse.cmjddw2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
