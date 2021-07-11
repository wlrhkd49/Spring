package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // aop 로 적용
@Component // spring 에서 관리하도록 함
public class ParameterAop {

    // controller 하위에 모든 메소드 들을 aop에 적용
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // 메소드 이름은 달라도 됨 어노테이션이 중요
    private void cut() {

    }

    // 메소드 실행 전 argument 확인
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        //메소드 이름 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());



        Object[] args = joinPoint.getArgs(); // 매개변수들의 배열

        for(Object obj : args) {
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    // 리턴 값 확인
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj");
        System.out.println(returnObj);
    }

}
