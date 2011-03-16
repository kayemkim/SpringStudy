package nephilim.study.spring.ch06.m03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionHandler implements InvocationHandler{
	
	private Object target;
	private PlatformTransactionManager txManager;
	private String pattern;

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
		System.out.println("TransactionHandler.invoke()");
		if( method.getName().startsWith(pattern)){
			return invokeWithTx(method, args);
		} else {
			return method.invoke(target, args);
		}
	}
	
	public Object invokeWithTx(Method method, Object[] args) throws Throwable {
		TransactionStatus txStatus = txManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object ret = method.invoke(target, args);
			txManager.commit(txStatus);
			return ret;
		} catch ( InvocationTargetException e) {
			System.out.println("ROLLBACKED!");
			txManager.rollback(txStatus);
			throw e.getTargetException();
		}
	}

	
}
