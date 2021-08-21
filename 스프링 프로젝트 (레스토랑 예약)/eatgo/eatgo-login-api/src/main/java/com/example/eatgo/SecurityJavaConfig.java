package com.example.eatgo;

import com.example.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    //@Value("${jwt.secret}")
    private String secret = "12345678901234567890123456789012";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().disable() // 보안에 관한 것
                .csrf().disable()
                .formLogin().disable() // 로그인 폼을 없애줌
        .headers().frameOptions().disable(); // h2-console 헤더 보이게 하기
        // 필터 추가
        // and()로 초기화
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }
}
