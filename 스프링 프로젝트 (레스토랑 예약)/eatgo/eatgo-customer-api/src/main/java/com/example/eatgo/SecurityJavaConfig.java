package com.example.eatgo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable() // 보안에 관한 것
                .csrf().disable()
                .formLogin().disable() // 로그인 폼을 없애줌
        .headers().frameOptions().disable(); // h2-console 헤더 보이게 하기
    }

}
