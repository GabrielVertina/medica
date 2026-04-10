package com.example.medica.dto;

public class RetornaTokenDto {
 private  String jwtoken;

    public RetornaTokenDto(String jwtoken) {
        this.jwtoken = jwtoken;
    }

    public String getJwtoken() {
        return jwtoken;
    }

}

