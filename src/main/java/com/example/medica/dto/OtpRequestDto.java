package com.example.medica.dto;

public class OtpRequestDto {

        private String email;
    private String otpCode;

    public OtpRequestDto(){

    }

    public OtpRequestDto(String email, String optCode){
        this.email = email;
        this.otpCode = otpCode;

    }

        public String getEmail() {
            return email;
        }
    }


