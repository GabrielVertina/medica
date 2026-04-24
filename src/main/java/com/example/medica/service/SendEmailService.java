package com.example.medica.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
private final JavaMailSender mailSender;

public SendEmailService(JavaMailSender mailSender){
    this.mailSender =mailSender;

}
public void sendOtp(String to, String code){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
message.setSubject("Seu codigo de verificacao ");
message.setText("Seu codigo OTP e"+code+"\nEle Expira em 10 minutos");
mailSender.send(message);
}


}
