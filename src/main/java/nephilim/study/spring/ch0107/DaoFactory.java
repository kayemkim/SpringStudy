package nephilim.study.spring.ch0107;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DaoFactory {
	
	@Bean
	@Scope("prototype")
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		ConnectionMaker connMaker = connectionMaker();
		userDao.setConnectionMaker(connMaker);
		return userDao;
	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(actualConnectionMaker());
	}
	
	@Bean
	public ConnectionMaker actualConnectionMaker() {
		return new NConnectionMaker();
	}
}
