package com.example.medica.dto;

public class RetornaOtpDto {
    private String otpCode;

    public RetornaOtpDto(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getOtpCode() {
        return otpCode;

    }
}