/**
 * 
 */
package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author antPinot
 *
 */

@Aspect
@Component
public class ExecutionTimeServiceMethodsAspect {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("execution(* com.example.demo.controller..*(..))")
	public Object ExecutionTimeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object object =joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		logger.info("Method " + joinPoint.getSignature().getName() + " executed in " + executionTime + " ms");
		return object;
	}

}
