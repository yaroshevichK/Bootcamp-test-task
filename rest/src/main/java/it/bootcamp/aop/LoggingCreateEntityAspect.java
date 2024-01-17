package it.bootcamp.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static it.bootcamp.util.ConstantsRest.COMPLETE_ADD_ENTITY;
import static it.bootcamp.util.ConstantsRest.START_ADD_ENTITY;

@Log4j2
@Aspect
@Component
public class LoggingCreateEntityAspect {
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void anyServiceMethods() {
    }

    @Pointcut("execution(* create*(..))")
    public void createMethods() {
    }


    @Before("anyServiceMethods() && createMethods()")
    public void beforeCreateMethodsLoggingAdvice(JoinPoint joinPoint) {
        log.info(START_ADD_ENTITY);
        Arrays.stream(joinPoint.getArgs()).forEach(log::info);
    }


    @AfterReturning("anyServiceMethods() && createMethods()")
    public void afterCreateMethodsLoggingAdvice() {
        log.info(COMPLETE_ADD_ENTITY);
    }

}
