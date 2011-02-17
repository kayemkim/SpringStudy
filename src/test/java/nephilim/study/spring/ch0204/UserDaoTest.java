package nephilim.study.spring.ch0204;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/nephilim/study/spring/ch0204/test-applicationContext.xml")

public class UserDaoTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Before 
	public void confirmAppContext() {
		System.out.println( context );
	}
	
	@Test
	public void T1(){
		System.out.println( String.format("%h %h",context, this));
		
	}
	
	@Test
	public void T2(){
		System.out.println( String.format("%h %h",context, this));
		
	}
	
	
	
}
