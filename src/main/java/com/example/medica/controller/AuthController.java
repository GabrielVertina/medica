package com.example.medica.controller;

import com.example.medica.auth.AuthResponseDTO;
import com.example.medica.auth.AuthService;
import com.example.medica.auth.LoginRequestDTO;
import com.example.medica.auth.RegisterRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

public AuthController(AuthService service){
    this.service = service;
}

@PostMapping("/register")
public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto){
    return service.register(dto);
}

@PostMapping("/login")
public AuthResponseDTO login(@RequestBody LoginRequestDTO dto){
return service.login(dto);
}
}
