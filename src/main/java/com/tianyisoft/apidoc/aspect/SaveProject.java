package com.tianyisoft.apidoc.aspect;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.repository.ProjectRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

@Aspect
public class SaveProject {
    @Autowired
    private ProjectRepository projectRepository;

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
        Object[] args = pjp.getArgs();
        int id = ((Project)args[0]).getId();
        Project old = projectRepository.findById(id).orElse(new Project());
        ((Project)args[0]).setCreatedAt(old.getCreatedAt());
        Object result = pjp.proceed(args);
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
