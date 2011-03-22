package springbook.user.service.mongo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.mongodb.DB;

public class MongoTransactionAdvice implements MethodInterceptor {

	DB db;
	
	public void setDb(DB db) {
		this.db = db;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object ret = null;
		try {
			ret = invocation.proceed();
			db.requestStart();
			
		} catch(RuntimeException e) {
			// MongoDB 복구 처리
			e.printStackTrace();
		} finally {
			db.requestDone();
		}
		return ret;
	}

}
