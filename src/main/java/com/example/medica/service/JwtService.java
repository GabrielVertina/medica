package com.example.medica.service;
import com.example.medica.domain.UserRoot;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {


   private final Key key = Keys.secretKeyFor(SignatureAlgorithm.ES512);
    public String generateToken(UserRoot userRoot){
     return Jwts.builder()
             .setSubject(userRoot.getEmail())
             .setIssuedAt(new Date())
.setExpiration(new Date(
System.currentTimeMillis()+1000*60*60)
).signWith(key).compact();


    }

public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key).
                        build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
}

}
