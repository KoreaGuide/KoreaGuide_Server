package com.example.koreaguide.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component

public class JwtUtil {

    public String createAccessToken(Long id, String name){
        //TODO: JJWT 사용
        String secret = "12345678901234567890123456789012";

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .claim("userId",id)
                .claim("name",name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }
}
