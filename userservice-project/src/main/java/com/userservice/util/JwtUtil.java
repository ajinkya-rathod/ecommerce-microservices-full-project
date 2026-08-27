package com.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@Getter
@Setter
@AllArgsConstructor
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private final SecretKey key;

    @Value("${app.jwt.expiration-ms}")
    private final Long ExpirationMs;

    public String generateToken(Long userid,String email,String role){
        Long now = System.currentTimeMillis();
    return Jwts.builder()
                .subject(String.valueOf(userid))
                .claim("email",email)
                .claim("role",role)
                .issuedAt(new Date(now))
                .expiration(new Date(ExpirationMs))
                .signWith(key)
                .compact();


    }

    public Jws<Claims> validateToken(String token){
       return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    }

    public Long getUserIdFromToken(String token){
        Claims claims = validateToken(token).getBody();
        return Long.valueOf(claims.getSubject());
    }
}
