package com.example.medica.service;


import com.example.medica.util.OtpUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
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
String code = OtpUtil.generateOtp(10);
String key = PREFIX + code;
redisTemplate.opsForValue().set(key,email,EXPIRATION_MINUTES, TimeUnit.MINUTES);
  return code;
    }

public String validaOtp( String inputCode) {
    String key = PREFIX + inputCode;
 String email = redisTemplate.opsForValue().get(key);
if(email == null){
    throw new RuntimeException("CODIGO EXPIRADO OU NAO ENCONTRADO");
}
redisTemplate.delete(key);

return email;

    }

}

