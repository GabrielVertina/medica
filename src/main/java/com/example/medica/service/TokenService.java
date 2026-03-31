package com.example.medica.service;

import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
private PrivateKey privateKey;   
private PublicKey publicKey;
private Date date;
public TokenService(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
}

public void JwtService(String privateKeyPath, String publicKeyPath){
}

private PrivateKey loadPrivateKey() throws Exception {

        String key = new String (Files.readAllBytes(Paths.get("C:\\Users\\gabri\\medica\\src\\main\\resources\\keys\\private-key.pem")));
        
key.replace("-----BEGIN PRIVATE KEY-----","")
.replace("-----END PRIVATE KEY-----","")
.replaceAll("\\s", "");

byte[] decode = Base64.getDecoder().decode(key);

PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);

KeyFactory keyFactory = KeyFactory.getInstance("EC");
     
return keyFactory.generatePrivate(spec);
}

private PublicKey loadPublicKey() throws Exception{

String key = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\keys\\public-key.pem")));
key.replace("-----BEGIN PUBLIC KEY-----", "")
.replace("-----END PUBLIC KEY-----","")
.replaceAll("\\s","");

byte[] decode = Base64.getDecoder().decode(key);
X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
KeyFactory keyFactory = KeyFactory.getInstance("EC");
return keyFactory.generatePublic(spec);
}

public String generateToken(String email){
return  Jwts.builder()
.setSubject(email)
.setIssuedAt(date)
.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*4))
.signWith(privateKey, SignatureAlgorithm.ES512)
.compact();
}

public Boolean validateToken(String jwtoken){
        try{
        Jwts.parserBuilder()
        .setSigningKey(publicKey)
.build()
.parseClaimsJws(jwtoken);
return true;
}
catch(ExpiredJwtException e){

System.out.print("Token expirado."+e.getMessage());

}
catch(SignatureException e){
System.out.print("Token não assinado."+e.getMessage());

}

return false;
}

}
