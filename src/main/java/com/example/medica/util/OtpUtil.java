package com.example.medica.util;

import java.security.SecureRandom;

public class OtpUtil {
    private static final SecureRandom randomNumber = new SecureRandom();
public static String generateOtp(int length){
StringBuilder otp = new StringBuilder();
for(int i=0;i<length;i++) {
    otp.append(randomNumber.nextInt(10));
}
return otp.toString();
}
}
