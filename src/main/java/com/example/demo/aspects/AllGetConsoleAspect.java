/**
 * 
 */
package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
public class AllGetConsoleAspect {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@After("execution(* com.example.demo..get*(..))")
	public void consoleService(JoinPoint joinPoint) {
		logger.info("Get Method Invoked");
	}

}
