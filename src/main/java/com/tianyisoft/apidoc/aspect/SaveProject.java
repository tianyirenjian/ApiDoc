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

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        try {
            int id = ((Project)args[0]).getId();
            Project old = projectRepository.findById(id).orElse(new Project());
            ((Project)args[0]).setCreatedAt(old.getCreatedAt());
            ((Project)args[0]).setUserId(old.getUserId());
        } catch (Throwable ignored) {
        }
        return pjp.proceed(args);
    }
}
