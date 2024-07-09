package com.huntercodexs.demo.annotation.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Help4DevsLoggerAspect {

    @Around("@annotation(Help4DevsLoggerAnnotation)")
    public Object doLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("Running doLog");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("Finishing doLog");

        return result;

    }

}
