package com.example.springsecurity.controller;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import java.util.*;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(
                User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초가입시 USER
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(()->new IllegalIdentifierException("가입 x email"));
        if(!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalIdentifierException("잘못된 비번입니다.");
        }

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

}
