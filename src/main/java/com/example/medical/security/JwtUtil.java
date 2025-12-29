package com.example.medical.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {

    private String secretKey;

    private long expiration_time_access_token;
    private long expiration_time_refresh_token;
 private Key key;
 private final Map<String,Long> blackList=new ConcurrentHashMap<>();
    public JwtUtil(
            @Value("${SECRET_KEY}") String secretKey,
            @Value("${ACCESS_TOKEN_EXPIRATION_TIME}") long expiration_time_access_token,
            @Value("${REFERESH_TOKEN_EXPIRATION_TIME}") long expiration_time_refresh_token
    ) {
        this.secretKey = secretKey;
        this.expiration_time_refresh_token = expiration_time_refresh_token;
        this.expiration_time_access_token=expiration_time_access_token;
    }

@PostConstruct
    public void init() {
        // Correct way to generate a secure Key object from the secret string
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    // access token
public String generateAccessToken(String email, String role){
   Date expirationDate=new Date(System.currentTimeMillis()+expiration_time_access_token);
   return  Jwts.builder()
           .subject(email)
           .claim("role",role)
           .id(UUID.randomUUID().toString())
           .issuedAt(new Date())
           .expiration(expirationDate)
           .signWith(key,SignatureAlgorithm.HS256)
           .compact();
}
// refresh token generation
    public  String generateRefreshToken(String email){
        Date expirationDate=new Date(System.currentTimeMillis()+expiration_time_refresh_token);
        return Jwts.builder()
                .subject(email)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    // backlist refresh and access token
    public void blackListToken(String token){
        try {
            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            Claims claims=jwsClaims.getBody();
            String jti= claims.getId();
            Date expiry=claims.getExpiration();
            blackList.put(jti,expiry.getTime());

        } catch (Exception e) {
            System.out.println("Invalid token");
        }

    }

    // check if token is blacklist
    public boolean isTokenBlackListed(String token){
        try{
            Claims claims=Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String jti=claims.getId();
            Long expiry=blackList.get(jti);
            if(expiry ==null) return  false;

            if(expiry<System.currentTimeMillis()){
                blackList.remove(jti);
                return  false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

// exact email from given jwt token

    public  String ExtractEmail(String accessToken){
        Claims claims= Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
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
        return !isTokenBlackListed(token);

    }
    catch (JwtException e){
        System.out.println("Token has expired"+e.getMessage());
        return false;
    }

    }

}
