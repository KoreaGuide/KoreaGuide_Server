package com.example.koreaguide.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
    private Key key;
    public JwtUtil(String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String createAccessToken(Integer id, String name){
        //TODO: JJWT 사용
        System.out.println("HIHHIHHIHI ");
        String secret = "12345678901234567890123456789012";

        Key key = Keys.hmacShaKeyFor(secret.getBytes());


        String token = Jwts.builder()
                .claim("userId",id)
                .claim("name",name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println("TOKEN"+token);
        return token;
    }

    public Claims getClaims(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        System.out.println("Claim: "+claims);

        return claims;
    }
}
