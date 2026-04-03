package com.example.medica.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medica.dto.TokenDTO;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.service.UserServiceRegister;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.medica.service.UserServiceRegister;

@RestController
@RequestMapping("/auth")
public class AuthController {
   
    private final UserServiceRegister userServiceRegister;
    
    public AuthController(UserServiceRegister userServiceRegister){
        this.userServiceRegister = userServiceRegister;
    }

    @PostMapping("/register")
public AuthController()





}

}







   }




    

    




