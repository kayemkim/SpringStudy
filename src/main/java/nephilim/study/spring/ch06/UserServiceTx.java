package nephilim.study.spring.ch06;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService{
	
	UserService userService;
	PlatformTransactionManager txManager;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransasctionManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}
	
	public void add(User user) {
		userService.add(user);
	}
	
	public void upgradeLevels() {
		TransactionStatus txStatus = txManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
			txManager.commit(txStatus);
		} catch ( RuntimeException e) {
			txManager.rollback(txStatus);
			throw e;
		}
	}
	
	class TestUserServiceExcetion extends RuntimeException {}
}
