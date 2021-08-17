package com.example.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

public class JwtUtil {

    private Key key;

    /**
     * JWT 사용
     */
    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name) {

        return Jwts.builder()
                .claim("userId", userId).claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token) //sign이 포함된 jwt
                .getBody();
    }
}
