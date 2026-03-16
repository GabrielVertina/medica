package com.example.medica.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medica.model.UserRoot;
import com.example.medica.repository.UserRepository;
import com.example.medica.service.JwtService;
@Service
public class RegisterService {
    
private final UserRepository repository;
private final PasswordEncoder encoder;
private final JwtService jwtService;

public RegisterService( UserRepository repository, PasswordEncoder encoder,JwtService jwtService){
    this.jwtService = jwtService;
this.encoder = encoder;
this.repository = repository;
}
public AuthResponseDTO register(RegisterRequestDTO regDto){
    UserRoot userRoot = new UserRoot();
userRoot.setName(regDto.getName());
userRoot.setEmail(regDto.getEmail());
if (repository.existsByEmail(regDto.getEmail())) {
    throw new RuntimeException("Email ja cadastrado.");
}
    
userRoot.setPassword(encoder.encode(regDto.getPassword()));
repository.save(userRoot);

String token = jwtService.generateToken(userRoot.getEmail());
return new AuthResponseDTO(token);


}
}
