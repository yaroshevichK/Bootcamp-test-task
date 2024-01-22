package it.bootcamp.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static it.bootcamp.util.ConstantsRest.REST_CONTROLLER_FINISH;
import static it.bootcamp.util.ConstantsRest.REST_CONTROLLER_RUN;
import static it.bootcamp.util.ConstantsRest.SERVICE_METHOD_FINISH;
import static it.bootcamp.util.ConstantsRest.SERVICE_METHOD_RUN;

@Log4j2
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void anyServiceMethods() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void anyControllers() {
    }

    @Before("anyServiceMethods()")
    public void beforeAnyServiceMethodsLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(SERVICE_METHOD_RUN,
                signature.getName(),
                signature.getMethod().getDeclaringClass());
    }

    @After("anyServiceMethods()")
    public void afterAnyServiceMethodsLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(SERVICE_METHOD_FINISH,
                signature.getName(),
                signature.getMethod().getDeclaringClass());
    }

    @Before("anyControllers()")
    public void beforeAnyControllersLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(REST_CONTROLLER_RUN,
                signature.getMethod().getDeclaringClass(),
                signature.getName());
    }

    @After("anyControllers()")
    public void afterAnyControllersLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(REST_CONTROLLER_FINISH,
                signature.getMethod().getDeclaringClass(),
                signature.getName());
    }
}
