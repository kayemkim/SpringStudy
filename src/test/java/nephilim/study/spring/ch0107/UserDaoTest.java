package nephilim.study.spring.ch0107;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

// No Extends
public class UserDaoTest{
	
	@Test
	public void CountingConnectionMaker가제대로카운팅되는지확인(){
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		List<UserDao> daoList = new ArrayList<UserDao>();
		for (int i = 0; i < 5; i++) { 
			UserDao dao = context.getBean("userDao", UserDao.class);
			daoList.add(dao);
		}
		
		//System.out.println(daoList);
		for (UserDao userDao : daoList) {
			System.out.println(userDao);
		}
		
		CountingConnectionMaker countingConnMaker = context.getBean("connectionMaker", 
				CountingConnectionMaker.class);
		System.out.println(String.format("Count = [%d]", countingConnMaker.getCounter()));
	}
	
	@Test
	public void XML설정으로부터제대로Bean을반환하는지확인() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDaoTest.class);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		System.out.println(userDao);
	}
	
	@Test
	public void SimpleDriverDataSource테스트() {
		//SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDaoTest.class);
		SimpleDriverDataSource datasource = context.getBean("myDataSource", SimpleDriverDataSource.class);
		
	}
}
