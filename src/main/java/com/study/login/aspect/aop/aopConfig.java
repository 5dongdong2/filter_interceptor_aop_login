package com.study.login.aspect.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
public class aopConfig {

//    @Around("execution(com.study.login.service..*.*())")
    @Around("execution(com.study.login.service.LoginService.*(..)")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        Long measuredTime = stopWatch.getTotalTimeMillis();

        String className = joinPoint.getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("[{}] Execution Time=[{}]", className+"."+methodName, measuredTime + "(ms)");

        return result;
    }

    @Before("execution(com.study.login..*.*())")
    public void logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("method name=[{}]", methodName);
    }

}
