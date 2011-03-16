package nephilim.study.spring.ch0106;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DaoFactory {
	
	@Bean
	@Scope("prototype")
	public UserDao userDao() {
		return new UserDao();
	}
}
