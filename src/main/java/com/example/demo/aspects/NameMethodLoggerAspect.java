/**
 * 
 */
package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author antPinot
 *
 */

@Aspect
@Component
public class NameMethodLoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Before("execution(* com.example.demo.controller..*(..))")
	public void MethodLoggerService(JoinPoint joinPoint) {
		logger.info("Executing controller method {}", joinPoint.getSignature().getName());

	}

}
