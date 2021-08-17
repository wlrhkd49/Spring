package com.example.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;


class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {

        String token = jwtUtil.createToken(1004L, "John");

        assertEquals(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "..."; // 토큰 넣기
        Claims claims = jwtUtil.getClaims(token);

        assertEquals("John", claims.get("name"));
        assertEquals(1004L, claims.get("userId", Long.class));
    }

}