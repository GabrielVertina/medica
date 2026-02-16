package com.example.medica.auth;

import com.example.medica.repository.UserRepository;
import com.example.medica.service.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

private final UserRepository repository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;

public AuthService( UserRepository repository, PasswordEncoder passwordEncoder,JwtService jwtService){
    this.jwtService = jwtService;
this.passwordEncoder = passwordEncoder;
this.repository = repository;
}

public AuthResponseDTO register(RegisterRequestDTO dto){

        User user = User.builder().

}




}
