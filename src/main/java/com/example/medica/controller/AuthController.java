package com.example.medica.controller;

import com.example.medica.dto.*;
import com.example.medica.service.ServiceOTP;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medica.service.UserServiceRegister;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.medica.service.UserServiceRegister;

@RestController
@RequestMapping("/auth")
public class AuthController {
   
    private final UserServiceRegister userServiceRegister;
    private final ServiceOTP serviceOTP;
    public AuthController(UserServiceRegister userServiceRegister, ServiceOTP serviceOTP){
        this.userServiceRegister = userServiceRegister;
    this.serviceOTP = serviceOTP;
    }

    @PostMapping("/register")
public RetornaTokenDto userRegister (@RequestBody UserDtoRegister dto) throws Exception {

return userServiceRegister.userRegister(dto);


}



}










    

    




