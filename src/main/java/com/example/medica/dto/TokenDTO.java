package com.example.medica.dto;


public class TokenDTO {

    private String jwtoken;

    public TokenDTO(String jwtoken) {
        this.jwtoken = jwtoken;
    }

    public String getJwtoken() {
        return jwtoken;
    }

}


