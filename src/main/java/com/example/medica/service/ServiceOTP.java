package com.example.medica.service;

import com.example.medica.dto.OtpDto;
import com.example.medica.dto.UserDtoRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ServiceOTP {

@Autowired
private StringRedisTemplate redisTemplate;
private JavaMailSender mailSender;


public OtpDto generateOtp(UserDtoRegister userDtoRegister){
    String email = userDtoRegister.getEmail();

    String otp = String.valueOf((int)(Math.random()*900000)+100000);
redisTemplate.opsForValue().set("otp:" + email ,otp, Duration.ofMinutes(5));
sendEmail(email,otp);
return new OtpDto(email);
}

private void sendEmail(String to,String otp){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
message.setSubject("Codigo de verificacao");
    message.setText("Seu código OTP é: " + otp);
    mailSender.send(message);
}

}
