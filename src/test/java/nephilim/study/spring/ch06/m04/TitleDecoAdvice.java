package nephilim.study.spring.ch06.m04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TitleDecoAdvice implements MethodInterceptor {
	public static final String DECO_STR = "--------------title---------------";
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("TitleDecoAdvice.invoke()");
		
		String returnValue = (String)invocation.proceed();
		
		
		return DECO_STR + "\n" + returnValue + "\n" + DECO_STR;
	}
}