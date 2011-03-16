package nephilim.study.spring.ch06.m03;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

public class TimeMeasureProxyFactoryBean implements FactoryBean<Object>{
	
	private Object target;
	private Class<?> serviceInterface;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public Object getObject() throws Exception {
		System.out.println("TimeMeasureProxyFactoryBean.getObject()" + this + " target:" + target);
		TimeMeasureHandler timeMeasureHandler = new TimeMeasureHandler();
		timeMeasureHandler.setTarget(target);
		
		ClassLoader classLoader = getClass().getClassLoader();
		Object result = Proxy.newProxyInstance(classLoader, new Class[]{serviceInterface}, timeMeasureHandler);
		System.out.println( "proxied = " + result );
		return result;
	}

	public Class<?> getObjectType() {
		return serviceInterface;
	}

	public boolean isSingleton() {
		return false;
	}
	
}
