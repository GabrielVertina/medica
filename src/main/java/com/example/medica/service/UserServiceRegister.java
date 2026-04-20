package com.example.medica.service;
import com.example.medica.dto.RetornaTokenDto;
import com.example.medica.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.medica.service.SendEmailService;
import com.example.medica.dto.TokenDTO;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.entity.User;
import com.example.medica.service.TokenService;
@Service
public class UserServiceRegister {
    
    private final UserRepository userRepository;
@SuppressWarnings("unused")
private final PasswordEncoder passwordEncoder;

private final TokenService tokenService;

private final SendEmailService sendEmail;

    public UserServiceRegister(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, SendEmailService sendEmail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
this.tokenService = tokenService;
this.sendEmail = sendEmail;
    }


    public RetornaTokenDto userRegister(UserDtoRegister userDtoRegister) throws Exception {
        User user = new User();

        user.setName(userDtoRegister.getName());
        user.setEmail(userDtoRegister.getEmail());


// se o usuario inserir um email que ja esta cadastrado no banco, ou seja ja passou pelo jpa, retorna exceção;
        if (userRepository.existsByEmail(userDtoRegister.getEmail())) {
            throw new Exception("Email ja cadastrado");
        }
        user.setPassword(passwordEncoder.encode(userDtoRegister.getPassword()));
        userRepository.save(user);
String mail = sendEmail.SendOtpEmail(user.getEmail());

if(user.getVerified() == true) {
            String token = tokenService.generateToken(user.getEmail());
            return new RetornaTokenDto(token);
        }
else{
    throw  new Exception("Usuario nao verificado");

        }

    }


}

