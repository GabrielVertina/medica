package com.example.medica.service;

import com.example.medica.dto.OtpDto;
import com.example.medica.dto.OtpRequestDto;
import com.example.medica.dto.UserDtoRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ServiceOTP {


    private final RedisTemplate<String, String> redisTemplate;
private static final String PREFIX = "otp:";
private static final long EXPIRATION_MINUTES = 10;

    public ServiceOTP(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public String generateOTP(String email){
String code = String.format("%0d6",new SecureRandom().nextInt(999999));
String key = PREFIX + email;
redisTemplate.opsForValue().set(key,code,EXPIRATION_MINUTES, TimeUnit.MINUTES);
  return code;
    }
public boolean validaOtp(String email, String inputCode) {
    String key = PREFIX + email;
    String storedCode = redisTemplate.opsForValue().get(key);
    if (storedCode == null) {
        throw new RuntimeException();
    } else if (!storedCode.equals(inputCode)) {
throw new RuntimeException();
    }
redisTemplate.delete(key);
    return true;
    }}

