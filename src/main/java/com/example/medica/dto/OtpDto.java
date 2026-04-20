package com.example.medica.dto;

public class OtpDto {
 private String otpCode;
 public OtpDto(String otpCode){
 this.otpCode = otpCode;
 }

public String getOtp(){
     return otpCode;
}
}
