package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.UserLoginDto;
import com.ijse.cmjddw2.dto.requestDto.UserRegisterDto;
import com.ijse.cmjddw2.entity.UserEntity;
import com.ijse.cmjddw2.repository.UserRepository;
import com.ijse.cmjddw2.security.jwt.JwtUtils;
import com.ijse.cmjddw2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto userRegisterDto){
        if (userRepository.existsByEmail(userRegisterDto.getEmail())){
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        UserEntity user = new UserEntity();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setEmail(userRegisterDto.getEmail());
       return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(jwt);

    }

}
