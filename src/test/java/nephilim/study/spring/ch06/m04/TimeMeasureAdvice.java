package nephilim.study.spring.ch06.m04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeMeasureAdvice implements MethodInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("TimeMeasureAdvice.invoke()");
		
		long start = System.currentTimeMillis();
		String returnValue = (String)invocation.proceed();
		long ellapsed = System.currentTimeMillis() - start;
		
		System.out.printf("** ellapsed time =%d%n", ellapsed);
		return returnValue;
	}
}