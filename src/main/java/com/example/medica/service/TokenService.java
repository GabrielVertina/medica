package com.example.medica.service;

import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
private PrivateKey privateKey;   
private PublicKey publicKey;

public void JwtService(String privateKeyPath, String publicKeyPath){
}


private PrivateKey loadPrivateKey(String Path) throws Exception {

        String key = new String (Files.readAllBytes(Paths.get("C:\\Users\\gabri\\medica\\src\\main\\resources\\keys\\private-key.pem")));
        
key.replace("-----BEGIN PRIVATE KEY-----","")
.replace("-----END PRIVATE KEY-----","")
.replaceAll("\\s", "");

byte[] decode = Base64.getDecoder().decode(key);

PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);

KeyFactory keyFactory = KeyFactory.getInstance("EC");
     
return keyFactory.generatePrivate(spec);
}







private PublicKey loadPublicKey(String Path) throws Exception{

String key = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\keys\\public-key.pem")));
key.replace("-----BEGIN PUBLIC KEY-----", "")
.replace("-----END PUBLIC KEY-----","")
.replaceAll("\\s","");

byte[] decode = Base64.getDecoder().decode(key);
X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
KeyFactory keyFactory = KeyFactory.getInstance("EC");
return keyFactory.generatePublic(spec);
}




}