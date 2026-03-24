package com.example.medica.service;

import java.security.PublicKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
private PrivateKey privateKey;   
private PublicKey publicKey;

public void JwtService(String privateKeyPath, String publicKeyPath){




}

private PrivateKey loadPrivateKey(String Path){
    try {
        String key = new String (Files.readAllBytes(Paths.get("C:\\Users\\gabri\\medica\\src\\main\\resources\\keys\\private-key.pem"   )));
        
key.replace("-----BEGIN PRIVATE KEY-----","");



     
    } catch (IOException e) {
        e.printStackTrace();
    }



}




}