package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//특정 메소드의 실행시간 측정
@Aspect
@Component // Bean은 클래스에 붙일 수 없음 -> 메소드에 사용
// 하나의 클래스에 여러가지 빈이 등록됨
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // 메소드 이름은 달라도 됨 어노테이션이 중요
    private void cut() {

    }

    // 패키지 하위에 Timer라는 어노테이션에 적용
    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {

    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed(); // 값이 리턴되면 Object에 들어감
        stopWatch.stop();

        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());

    }

}
