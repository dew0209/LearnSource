package base.class5.aop.service;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志切面类的方法需要动态感知到div()方法运行
 * 		通知方法：
 * 			前置通知：logStart() 在我们执行div()之前运行                                      @Before
 * 			后置通知：logEnd() 在我们执行div()运行之后，不管有没有异常						@After
 * 			返回通知：logReturn() 在我们执行div()运行之后，正常的返回，有异常就没有这个		@AfterReturning
 * 			异常通知：logException() 在我们执行div()出现异常时								@AfterThrowing
 * 			环绕通知：动态代理，需要手动执行joinPoint.proceed()								@Around
 *
 *
 * ---around---start
 * ---logStart---
 * ---around---end
 * ---logEnd---
 * ---logReturn---
 **/
@Aspect
@Component
public class LogAspects {

	@Pointcut("execution(public int base.class5.aop.service.CalcService.div(int,int))")
	public void pointCut(){

	}

	@Before(value = "pointCut()")
	public void logStart(JoinPoint joinPoint){
		System.out.println(joinPoint.getSignature().getName() + "---logStart---");
	}

	@After("pointCut()")
	public void logEnd(){
		System.out.println("---logEnd---");
	}

	@AfterReturning(value = "pointCut()",returning = "result")
	public void logReturn(Object result){
		System.out.println("---logReturn---" + result);
	}

	@AfterThrowing(value = "pointCut()",throwing = "exception")
	public void logException(Exception exception){
		System.out.println("---logException---" + exception);
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		System.out.println("---around---start---" + Arrays.toString(args));
		Object res = joinPoint.proceed();
		System.out.println("---around---end---" + res);
		return res;
	}
}
