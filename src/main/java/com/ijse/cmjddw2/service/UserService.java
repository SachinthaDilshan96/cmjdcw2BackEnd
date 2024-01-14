package com.ijse.cmjddw2.service;

import com.ijse.cmjddw2.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserByEmail(String email);
}
