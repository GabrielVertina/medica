package com.example.medica.service;
import com.example.medica.dto.RetornaTokenDto;
import com.example.medica.repository.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.entity.User;

@Service
public class UserServiceRegister {
    
    private final UserRepository userRepository;
@SuppressWarnings("unused")
private final PasswordEncoder passwordEncoder;
private final ServiceOTP serviceOTP;
private final TokenService tokenService;
private final SendEmailService sendMail;
    public UserServiceRegister(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, ServiceOTP serviceOTP, SendEmailService sendMail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
this.tokenService = tokenService;
this.serviceOTP = serviceOTP;
    this.sendMail = sendMail;
    }

    public void userRegister(UserDtoRegister userDtoRegister) throws Exception {
        User user = new User();
        user.setName(userDtoRegister.getName());
        user.setEmail(userDtoRegister.getEmail());
        if (userRepository.existsByEmail(userDtoRegister.getEmail())) {
            throw new Exception("Email ja cadastrado");
        }
        user.setPassword(passwordEncoder.encode(userDtoRegister.getPassword()));
user.setVerified(false);

        userRepository.save(user);
String code = serviceOTP.generateOTP(userDtoRegister.getEmail());
sendMail.sendOtp(userDtoRegister.getEmail(),code);

        



          //  String token = tokenService.generateToken(user.getEmail());


        }

        }




