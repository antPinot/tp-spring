/**
 * 
 */
package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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
public class SaveServiceExceptionLoggerAspect {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@AfterThrowing(value="execution(* com.example.demo.repository..save*(..))", throwing="ex")
	public void ExceptionLogger(JoinPoint joinPoint, Exception ex) {
		logger.error("Exception " + ex.getMessage() + " in method " + joinPoint.getSignature().getName());
	}
}
