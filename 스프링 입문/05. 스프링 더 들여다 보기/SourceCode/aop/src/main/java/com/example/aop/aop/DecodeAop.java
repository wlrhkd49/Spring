package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

// 값 변환을 위한 AOP
@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // 메소드 이름은 달라도 됨 어노테이션이 중요
    private void cut() {

    }

    // 패키지 하위에 Timer라는 어노테이션에 적용
    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableDecode() {

    }

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {

        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
            if(arg instanceof User){ //arg의 인스턴스가 유저인 경우
                User user = User.class.cast(arg); //User로 형변환
                String base64Email = user.getEmail(); // encoding email 꺼냄
                String email =new String(Base64.getDecoder().decode(base64Email), "UTF-8"); // decode 실행
                user.setEmail(email);
            }
        }

    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if(returnObj instanceof User) {
            User user = User.class.cast(returnObj); //User로 형변환
            String email = user.getEmail(); // 평문 이메일 꺼냄
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes()); // 인코딩
            user.setEmail(base64Email);
        }
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}
