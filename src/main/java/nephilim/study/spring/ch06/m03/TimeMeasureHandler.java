package nephilim.study.spring.ch06.m03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeMeasureHandler implements InvocationHandler {

	private Object target;
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("TimeMeasureHandler.invoke()");
		
		long started = System.currentTimeMillis();
		Object returnValue = method.invoke(target, args);
		long ellapsed =  System.currentTimeMillis() - started;
		
		System.out.printf("ellapsed time = %d%n",ellapsed);
		return returnValue;
	}
}