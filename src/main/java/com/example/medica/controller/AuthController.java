package com.example.medica.controller;

import com.example.medica.dto.*;
import com.example.medica.service.ServiceOTP;
import com.example.medica.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medica.service.UserServiceRegister;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
   
    private final UserServiceRegister userServiceRegister;
    private final ServiceOTP serviceOTP;
    private final TokenService tokenService;

    public AuthController(UserServiceRegister userServiceRegister, ServiceOTP serviceOTP, TokenService tokenService){
        this.userServiceRegister = userServiceRegister;
    this.serviceOTP = serviceOTP;
   this.tokenService = tokenService;
    }

    @PostMapping("/register")
public ResponseEntity<String> userRegister(@RequestBody UserDtoRegister dto) throws Exception {
        String message = userServiceRegister.userRegister(dto);
        return ResponseEntity.ok(message);
    }

    
@PostMapping("/verify-otp")
public ResponseEntity<RetornaTokenDto> verifiedUser(@RequestBody OtpRequestDto dto){
    serviceOTP.validaOtp(dto.getEmail(),dto.getOtpCode());
userServiceRegister.verifiedUser(dto.getEmail());
String token = tokenService.generateToken(dto.getEmail());

return ResponseEntity.ok(new RetornaTokenDto(token));
        }
}














    

    




