package com.infygo.airline.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for the specific method
    // Use the service interface in the pointcut so the advice will match even when Spring
    // creates a JDK dynamic proxy for the bean (default when an interface is present).
    @Before("execution(* com.infygo.airline.service.FlightService.addFlight(..))")
    @SuppressWarnings("unused")
    public void logMethodDetails(JoinPoint joinPoint) {
        // Method signature
        String methodSignature = joinPoint.getSignature().toShortString();

        // Current date and time
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Log output
        logger.info("Method called: {} at {}", methodSignature, formattedDateTime);
    }
}
