package com.example.medica.dto;

public class RetornaOtpDto {
    private String code;

    public RetornaOtpDto(String code) {
       this.code= code;
    }

    public String getOtpCode() {
        return code;

    }
}