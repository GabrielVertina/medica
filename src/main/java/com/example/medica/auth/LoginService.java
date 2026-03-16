package com.example.medica.auth;
import com.example.medica.model.UserRoot;
import com.example.medica.repository.UserRepository;
import com.example.medica.service.JwtService;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class LoginService {

private final UserRepository repository;
private final PasswordEncoder encoder;
private final JwtService jwtService;

public LoginService( UserRepository repository, PasswordEncoder encoder,JwtService jwtService){
    this.jwtService = jwtService;
this.encoder = encoder;
this.repository = repository;
}

public AuthResponseDTO login(LoginRequestDTO dto){
    UserRoot userRoot = repository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RuntimeException("Email invalido"));

if (!encoder.matches(dto.getPassword(),userRoot.getPassword())){
    throw new RuntimeException("Senha invalida");
}


String token = jwtService.generateToken(userRoot.getEmail());
return new AuthResponseDTO(token);

}



}
