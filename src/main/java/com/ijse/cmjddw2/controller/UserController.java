package com.ijse.cmjddw2.controller;

import com.ijse.cmjddw2.dto.requestDto.UserLoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<?> getUser(@RequestBody UserLoginDto userLoginDto){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
