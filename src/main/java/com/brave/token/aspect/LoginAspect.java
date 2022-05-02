package com.brave.token.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Around("execution(* com.brave.token.controller.TokenController.getToken(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info(" >>> aspect begin >>>");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info(" >>> aspect param >> {}", arg);
        }
        log.info(" >>> aspect end >>>");
        return pjp.proceed();
    }
}
