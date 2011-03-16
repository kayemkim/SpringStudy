package nephilim.study.spring.ch06.m04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UppercaseAdvice implements MethodInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("UppercaseAdvice.invoke()");
		
		String returnValue = (String)invocation.proceed();
		return returnValue.toUpperCase();
	}
}