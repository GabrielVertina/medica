package com.example.medica.auth;
import com.example.medica.repository.UserRepository;
import com.example.medica.service.JwtService;
import com.example.medica.domain.UserRoot;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

private final UserRepository repository;
private final PasswordEncoder encoder;
private final JwtService jwtService;

public AuthService( UserRepository repository, PasswordEncoder encoder,JwtService jwtService){
    this.jwtService = jwtService;
this.encoder = encoder;
this.repository = repository;
}

public AuthResponseDTO register(RegisterRequestDTO dto){

   UserRoot userRoot = new UserRoot();
userRoot.setName(dto.getName());
userRoot.setEmail(dto.getEmail());
userRoot.setPassword(encoder.encode(dto.getPassword()));
repository.save(userRoot);

String token = jwtService.generateToken(userRoot);
return new AuthResponseDTO(token);

}

public AuthResponseDTO login(LoginRequestDTO dto){
    UserRoot userRoot = repository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RuntimeException("Email invalido"));

if (!encoder.matches(dto.getPassword(),userRoot.getPassword())){
    throw new RuntimeException("Senha invalida");
}
return new AuthResponseDTO(jwtService.generateToken(userRoot));

}



}
