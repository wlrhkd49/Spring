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
    }

}