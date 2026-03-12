package com.example.medica.dto.authDto;

public class authResponseDTO {

    private String token;

    public authResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    
}
