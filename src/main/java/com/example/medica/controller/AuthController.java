package com.example.medica.controller;

import com.example.medica.dto.*;
import com.example.medica.service.ServiceOTP;
import com.example.medica.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.medica.service.UserServiceLogin;
import com.example.medica.service.UserServiceRegister;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
   
    private final UserServiceRegister userServiceRegister;
    private final ServiceOTP serviceOTP;
    private final TokenService tokenService;
private final UserServiceLogin userServiceLogin;

    public AuthController(UserServiceRegister userServiceRegister, ServiceOTP serviceOTP,
                          TokenService tokenService,UserServiceLogin userServiceLogin){
       this.userServiceLogin = userServiceLogin;
        this.userServiceRegister = userServiceRegister;
    this.serviceOTP = serviceOTP;
   this.tokenService = tokenService;
    }

    @PostMapping("/register")
public ResponseEntity<String> userRegister(@RequestBody UserDtoRegister dto) throws Exception {
        String message = userServiceRegister.userRegister(dto);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<RetornaTokenDto> userLogin(@RequestBody UserDtoLogin dto)throws Exception{
String login = userServiceLogin.userLogin(dto);
String token = tokenService.generateToken(dto.getEmail());

        return ResponseEntity.ok(
                new RetornaTokenDto(token)
        );

    }
    
@PostMapping("/verify-otp")
public ResponseEntity<RetornaTokenDto> verifiedUser(@RequestBody OtpRequestDto dto){
    String email = serviceOTP.validaOtp(dto.getOtpCode());

    userServiceRegister.verifiedUser(email);

    String token =
            tokenService.generateToken(email);

    return ResponseEntity.ok(
            new RetornaTokenDto(token)
    );
        }
}














    

    




