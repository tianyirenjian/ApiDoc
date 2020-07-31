package com.tianyisoft.apidoc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class SaveProject {
    @Pointcut("execution(* com.tianyisoft.apidoc.service.ProjectService.save(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before");
        Object result = pjp.proceed();
        System.out.println("around after");
        return result;
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("afterReturning");
    }
}
