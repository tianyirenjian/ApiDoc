package com.tianyisoft.apidoc.aspect;

import com.tianyisoft.apidoc.domain.Directory;
import com.tianyisoft.apidoc.repository.DirectoryRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class SaveDirectory {
    @Autowired
    private DirectoryRepository directoryRepository;

    @Pointcut("execution(* com.tianyisoft.apidoc.service.DirectoryService.save(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        try {
            int id = ((Directory)args[1]).getId();
            System.out.println(id);
            Directory old = directoryRepository.findById(id).orElse(new Directory());
            ((Directory)args[0]).setCreatedAt(old.getCreatedAt());
        } catch(Throwable ignored) {
        }
        return pjp.proceed(args);
    }
}
