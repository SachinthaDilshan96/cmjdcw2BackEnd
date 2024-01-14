package com.ijse.cmjddw2.security;

import com.ijse.cmjddw2.entity.UserEntity;
import com.ijse.cmjddw2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null){
            throw new UsernameNotFoundException("User with the given email not found");
        }
        return User.builder().username(user.getEmail()).password(user.getPassword()).build();
    }
}
