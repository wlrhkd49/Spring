package com.sp.fc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) //프리포스트로 권한체크 하겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 사용자 추가하는 메소드
    // Encoder 필요!
    // application.yml에 있는 정보들은 안됨
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.builder()
                .username("user2")
                .password(passwordEncoder().encode("2222"))
                .roles("USER")).withUser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("3333"))
                .roles("ADMIN"))
                ;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 홈페이지는 누구나 처음에 들어갈 수 있게 하는 메소드
    @Override
    // 어떤 필터를 어떻게 끼워넣어서 체인을 구성할 지 설정!
    protected void configure(HttpSecurity http) throws Exception {
        // 어떤 url에만 채우고 싶으면
        //http.antMatcher("/**") // 전체 url에 적용
//        http.authorizeRequests((requests) ->
//                requests.antMatchers("/").permitAll() // "/"는 누구나에게 접근 허용 (permitAll())
//                .anyRequest().authenticated()
//        );
//        http.formLogin();
//        http.httpBasic();
    }
}
