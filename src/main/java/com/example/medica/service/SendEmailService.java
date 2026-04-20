package com.example.medica.service;

import com.example.medica.util.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
@Autowired
    private JavaMailSender mailSender;
public String SendOtpEmail(String toEmail){
    String otp = OtpUtil.generateOtp(6);
    SimpleMailMessage message  = new SimpleMailMessage();
    message.setTo(toEmail);
message.setSubject("YOUR CODE IS");
    message.setText("YOUR OTP CODE IS: "+ otp +"\nIt will expire in 5 minutes.");
mailSender.send(message);
return otp;
}



}
