package nephilim.study.spring.ch05;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserService {
	
	UserDao dao;
	
	private DataSource dataSource;
	
	public void setUserDao(UserDao dao){
		this.dao = dao;
	}
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void upgradeLevels() throws Exception{
		TransactionSynchronizationManager.initSynchronization();
		Connection c = DataSourceUtils.getConnection(dataSource);
		c.setAutoCommit(false);
		
		try {
			List<User> users = dao.getAll();
			for(User user:users) {
				upgradeLevel(user);
			}
		} catch ( Exception e) {
			c.rollback();
			throw e;
		} finally {
			DataSourceUtils.releaseConnection(c, dataSource);
			TransactionSynchronizationManager.unbindResource(this.dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
		
	}
	
	public void upgradeLevel(User user) {
		if (user.upgradeLevel()) {
			dao.update(user);
		}
	}
}
