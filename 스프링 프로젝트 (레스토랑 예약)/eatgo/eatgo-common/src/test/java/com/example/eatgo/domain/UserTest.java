package com.example.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    public void creation() {
        User user= User.builder()
                .email("tester@example.com")
                .name("테스터")
                .level(100L)
                .build();

        assertEquals("테스터", user.getName());
        assertEquals(true, user.isAdmin());
        assertEquals(true, user.isActive());

        user.deactivate();

        assertEquals(false, user.isActive());
    }

    @Test
    public void accessTokenWithPassword() {
        User user = User.builder().password("ACCESSTOKEN").build();

        assertEquals("ACCESSTOKE", user.getAccessToken());

        user.setPassword("");

        assertEquals("", user.getAccessToken());
    }

    @Test
    public void accessTokenWithoutPassword() {
        User user = new User();

        assertEquals("", user.getAccessToken());
    }

}