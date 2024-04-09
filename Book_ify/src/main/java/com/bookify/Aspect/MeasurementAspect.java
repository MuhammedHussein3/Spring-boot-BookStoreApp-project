package com.bookify.Aspect;

import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class MeasurementAspect {

    Logger log = LoggerFactory.getLogger(MeasurementAspect.class);

    @Around("execution(* com.bookify.service.impl.*.*(..))")
    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("KPI:");
        sb.append("[").append(joinPoint.getKind()).append("]\tfor Return Type and Url: ").append(joinPoint.getSignature())
                .append("\n")
                .append("\tWithArgs: ");
        Object[] args = joinPoint.getArgs();
        String[] params = new String[args.length];
        for (int i=0;i<args.length;i++)
        {
            if (i<args.length-1)
                params[i] = args[i]+",";
            else
                params[i] = args[i]+"";
        }
        sb.append("(").append(StringUtils.join(params)).append(")");
        sb.append("\n");
        sb.append("\ttook time: ");
        Object returnValue = joinPoint.proceed();
        log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());

        return returnValue;

    }
}
