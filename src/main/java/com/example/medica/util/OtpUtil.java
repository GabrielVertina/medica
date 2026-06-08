package com.example.medica.util;

import java.security.SecureRandom;

public class OtpUtil {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static final String Caracteres = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz123456789";



    public static String generateOtp(int length) {

        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(Caracteres.length());
            otp.append(Caracteres.charAt(index));

        }
return otp.toString();
    }
}
