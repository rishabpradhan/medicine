package com.example.medical.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
   @Value("${secret_key}")
    private String secretKey;

@Value("${expiration_time}")
    private long expiration_time;
 private Key key;
@PostConstruct
    public void init() {
        // Correct way to generate a secure Key object from the secret string
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
public String generateToken(String email, String role){
   Date expirationDate=new Date(System.currentTimeMillis()+expiration_time);
   return  Jwts.builder()
           .subject(email)
           .claim("role",role)
           .issuedAt(new Date())
           .expiration(expirationDate)
           .signWith(key,SignatureAlgorithm.HS256)
           .compact();
}
// exact email from given jwt token

    public  String ExtractEmail(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    // validate token
    public boolean validateToken(String token){
    try{
        Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;

    }
    catch (JwtException e){
        return false;
    }

    }

}
