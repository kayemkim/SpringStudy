package nephilim.study.spring.ch0105;

import nephilim.study.spring.ch0105.DaoFactory;
import nephilim.study.spring.ch0105.UserDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UserDaoTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// JDK 1.5�� String.format�� �̿��Ͽ� ���
		System.out.println(String.format("UserDao = %s", dao));		
	}
}
