package com.ljw.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MarketInterceptor extends BaseInterceptor{

	@Pointcut("execution(* com.ljw.service.LoginService.*(..))")
	private void anyMethod() {
	}// 定义一个切入点

	@AfterThrowing(value = "anyMethod()", throwing = "ex")
	public void doThrowing(JoinPoint joinPoint, Throwable ex) {
		systemErrorLogger.info(formatErrLogMsg(joinPoint, ex, SystemId.MARKET));
		
	}
	
    @Around("anyMethod()")    
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {  
        long begin = System.currentTimeMillis();  
        Object retVal = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long useTime = end - begin;
        String resultCode = (String) retVal;
		
		systemAnalysisLogger.info(formatLogMsg(joinPoint, begin, useTime, resultCode, SystemId.MARKET));
		return retVal;
    } 
	
}
