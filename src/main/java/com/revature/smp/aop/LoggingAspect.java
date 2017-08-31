
package com.revature.smp.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * The Class AOP.
 */
@Component
@Aspect
public class LoggingAspect
{	
	/**
	 * Trace logging, surrounds the given point cut with error logging.
	 *
	 * @param pjp
	 *            the pjp
	 */
	@Around("everything()")
	public void traceLogging(ProceedingJoinPoint pjp) {
		
		// Setup for grabbing method information
		MethodSignature sign = (MethodSignature) pjp.getSignature();
		String[] paramNames = sign.getParameterNames();
		Class[] excepType = sign.getExceptionTypes();
		
		List<String> except = new ArrayList<>();
		for (Class c : excepType) {
			except.add(c.getSimpleName());
		}
		
		// Surround proceed in try catch
		try {
			pjp.proceed();
		} catch (Throwable e) {
			System.err.println("\nin Class:\t"
					+ sign.getDeclaringTypeName()
					+ "\nin Method:\t"
					+ sign.getName()
					+ "\nMethod exceptions:\n"
					+ except);
		}
		
	}
	
	/**
	 * Pointcut for everything.
	 */
	@Pointcut("execution(* *.smp.*.*(..))")
	public void everything() {
		
	}
	
}
