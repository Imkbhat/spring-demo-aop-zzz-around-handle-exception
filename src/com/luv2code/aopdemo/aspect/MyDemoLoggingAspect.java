package com.luv2code.aopdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private static  Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String method = joinPoint.getSignature().toShortString();
		myLogger.info(">>>>>>>>>>>>Method name WowWOwWowowow "+method);
		
		long begin = System.currentTimeMillis();
		
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch(Exception ex) {
			myLogger.warning(ex.getMessage());
			
			throw ex;
		}
		long ends = System.currentTimeMillis();
		
		long duration = ends - begin;
		
		myLogger.info("\n Duration========>"+duration / 1000.0 +" seconds");
		
		return result;
	}
	
	//after finally
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint thePoint) {
		//print which method
			String method = thePoint.getSignature().toShortString();
			myLogger.info(">>>>>>>>>>>>Method name @@@@@ "+method);
	}
	
	//After throwing aspect
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
			throwing="theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint thePoint, Throwable theExc) {
		
		//print which method
		String method = thePoint.getSignature().toShortString();
		myLogger.info(">>>>>>>>>>>>Method name"+method);
		
		//log the exception
		myLogger.info("\n Exception is"+theExc);
	}
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.combinedGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinpoints) {
		myLogger.info("\n=======>>> Excecuting @Before  Advice on method()");
		
		//display signature
		MethodSignature methodSig = (MethodSignature)theJoinpoints.getSignature();
		myLogger.info("Method======>"+methodSig);
	
	//display method arguments
		
		// get args
		Object[] args = theJoinpoints.getArgs();

		//loop thru args
		for (Object argus :  args) {
			myLogger.info(argus.toString());
			if (argus instanceof Account) {
				//downcast and print specific stuff
				Account theAccount = (Account) argus;
				
				myLogger.info("Acount name:"+theAccount.getName());
				myLogger.info("Acount level:"+theAccount.getLevel());
			}
		}
	
	}	
}
