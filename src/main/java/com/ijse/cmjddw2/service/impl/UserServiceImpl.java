package com.ijse.cmjddw2.service.impl;

import com.ijse.cmjddw2.entity.UserEntity;
import com.ijse.cmjddw2.repository.UserRepository;
import com.ijse.cmjddw2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
