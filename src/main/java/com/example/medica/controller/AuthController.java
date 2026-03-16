package com.example.medica.controller;
import com.example.medica.auth.AuthResponseDTO;
import com.example.medica.auth.LoginService;
import com.example.medica.auth.LoginRequestDTO;
import com.example.medica.auth.RegisterRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

public AuthController(LoginService loginService){
  this.loginService = loginService;
}




@PostMapping("/register")
public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto){
    return service.register(dto);
}

@PostMapping("/login")
public AuthResponseDTO login(@RequestBody LoginRequestDTO dto){
return loginService.login(dto);
}
}
