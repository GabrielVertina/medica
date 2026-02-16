package com.example.medica.auth;

public class AuthResponseDTO {

    private String Token;

    public AuthResponseDTO(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }
}
