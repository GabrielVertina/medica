package com.example.medica.service;

import com.example.medica.dto.OtpDto;
import com.example.medica.dto.OtpRequestDto;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.util.OtpUtil;
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

public class ServiceOTP {


    private final RedisTemplate<String, String> redisTemplate;
private static final String PREFIX = "otp:";
private static final long EXPIRATION_MINUTES = 10;

    public ServiceOTP(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public String generateOTP(String email){
String code = OtpUtil.generateOtp(6);
String key = PREFIX + email;
redisTemplate.opsForValue().set(key,code,EXPIRATION_MINUTES, TimeUnit.MINUTES);
  return code;
    }
public boolean validaOtp(String email, String inputCode) {
    String key = PREFIX + email;
    String storedCode = redisTemplate.opsForValue().get(key);
    if (storedCode == null) {
        throw new RuntimeException("Código OTP expirado ou não encontrado.");
    } else if (!storedCode.equals(inputCode)) {
throw new RuntimeException("Código OTP inválido.");
    }
redisTemplate.delete(key);
    return true;
    }}

