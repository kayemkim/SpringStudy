package nephilim.study.spring.ch06.m03;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class TxProxyFactoryBean implements FactoryBean<Object>{
	
	private Object target;
	private PlatformTransactionManager txManager;
	private String pattern;
	private Class<?> serviceInterface;
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public Object getObject() throws Exception {
		System.out.println("TxProxyFactoryBean.getObject()" + this + " target:" + target);
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setTxManager(txManager);
		txHandler.setPattern(pattern);
		
		ClassLoader classLoader = getClass().getClassLoader();
		Object result = Proxy.newProxyInstance(classLoader, new Class[]{serviceInterface}, txHandler);
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
