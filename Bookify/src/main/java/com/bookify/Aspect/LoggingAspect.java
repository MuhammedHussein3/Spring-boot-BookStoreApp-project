package com.bookify.Aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut(value = "execution(* com.bookify.repository..*(..))")
    public void forRepositoryLog(){}

    @Pointcut(value = "execution(* com.bookify.service.impl..*(..))")
    public void forServiceLog(){}

    @Pointcut(value = "execution(* com.bookify.controller..*(..))")
    public void forControllerLog(){}

    @Pointcut(value = "forRepositoryLog()||forServiceLog()||forControllerLog()")
    public void forAllApp(){}

    @Before(value = "forAllApp()")
    public void beforeMethod(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().toLongString();
        log.info("method name is ====> "+methodName);

        Object[] params = joinPoint.getArgs();
        int num_arg = 0;
        for (Object o : params){
            log.info("arg "+(++num_arg)+" is "+o);
        }
    }
}
