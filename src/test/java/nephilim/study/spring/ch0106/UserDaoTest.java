package nephilim.study.spring.ch0106;

import nephilim.study.spring.ch0106.DaoFactory;
import nephilim.study.spring.ch0106.UserDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UserDaoTest {
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao01 = context.getBean("userDao", UserDao.class);
		UserDao dao02 = context.getBean("userDao", UserDao.class);
		
		System.out.println(String.format("UserDao[01] = %s", dao01));		
		System.out.println(String.format("UserDao[02] = %s", dao02));		
	}
}
