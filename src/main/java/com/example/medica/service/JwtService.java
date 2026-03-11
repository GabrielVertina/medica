package com.example.medica.service;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.medica.security.UserDetailsServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {
private PrivateKey privateKey;
private PublicKey publicKey;
private  final Logger log = LoggerFactory.getLogger(getClass());


@PostConstruct
public void init() throws Exception{
      


InputStream privateStream = new ClassPathResource("keys/private-key.pem").getInputStream();

String privateKeyContent = new String(privateStream.readAllBytes())
.replace("-----BEGIN PRIVATE KEY-----", "")
.replace("-----END PRIVATE KEY-----", "")
 .replaceAll("\\s", "");


byte[] decodedPrivate = Base64.getDecoder().decode(privateKeyContent);
KeyFactory keyFactory = KeyFactory.getInstance("EC");
privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrivate));
    


InputStream publicStream = new ClassPathResource("keys/public-key.pem").getInputStream();
String publicKeyContent = new String(publicStream.readAllBytes())
.replace("-----BEGIN PUBLIC KEY-----", "")
.replace("-----END PUBLIC KEY-----", "")
.replaceAll("\\s","");

byte[] decodedPublic = Base64.getDecoder().decode(publicKeyContent);
publicKey = keyFactory.generatePublic(
new X509EncodedKeySpec(decodedPublic)
);

}


public String generateToken(String email) {
return Jwts.builder()
.setSubject(email)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
.signWith(privateKey,SignatureAlgorithm.ES512)
.compact();

}

public String extractUsername(String token){
return Jwts.parserBuilder()
.setSigningKey(publicKey)
.build()
.parseClaimsJws(token)
.getBody()
.getSubject();

}

public Boolean isTokenValid(String token,UserDetailsServiceImpl userDetails){
    try{
        Jwts.parserBuilder().setSigningKey(publicKey)
        .build()
        .parseClaimsJws(token);
        return true;
    }catch(Exception e){
         log.info("Access Deny, unauthorized user.");

    }
return false;

}


}










