package com.example.ioc.demo;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder") //Bean으로 등록된다.
public class Base64Encoder implements IEncoder {

    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
