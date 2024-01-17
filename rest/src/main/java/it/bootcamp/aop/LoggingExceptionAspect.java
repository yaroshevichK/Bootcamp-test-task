package it.bootcamp.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static it.bootcamp.util.ConstantsRest.EXCEPTION_HANDLER;
import static it.bootcamp.util.ConstantsRest.REST_CONTROLLER_EXCEPTION;

@Log4j2
@Aspect
@Component
public class LoggingExceptionAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void anyControllers() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void anyExceptionHandlerMethods() {
    }

    @AfterThrowing(pointcut = "anyControllers()",
            throwing = "exception")
    private void afterThrowingCreateMethodsLoggingAdvice(JoinPoint joinPoint, Throwable exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(REST_CONTROLLER_EXCEPTION,
                signature.getName(),
                signature.getMethod().getDeclaringClass(),
                exception.getMessage());
    }


    @After("anyExceptionHandlerMethods()")
    private void afterThrowingHandlerLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info(EXCEPTION_HANDLER,
                signature.getName(),
                signature.getMethod().getDeclaringClass());
    }
}
