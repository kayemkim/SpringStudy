package nephilim.study.spring.ch05;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:nephilim/study/spring/ch05/applicationContext.xml")
public class UserDaoTest {
	
	@Autowired
	ApplicationContext context;
	
	private User user1, user2, user3;
	
	@Before
	public void confirmAppContext() {
		System.out.println("UserDaoTest.confirmAppContext()");
		
		assertTrue( context != null);
	}
	
	@Before
	public void setUpUsers() {
		System.out.println("UserDaoTest.setUpUsers()");
		
		this.user1 = new User("id01", "±è°æ¹Î", "rlarudals", Level.GOLD, 2, 55 );
		this.user2 = new User("id02", "±èÀ±¿ì", "rladbsdn", Level.SILVER, 0, 0 );
		this.user3 = new User("id03", "ÀÌµ¿¿í", "dlehddnr", Level.SILVER, 1, 0 );
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		dao.add(user3);
	}
	
	@Test
	public void »ç¿ëÀÚÁ¶È¸() {
		UserDao dao = context.getBean("userDao", UserDao.class);
		List<User> users = dao.getAll();
		
		System.out.println(users);
		assertTrue( 3 == users.size());
	}
	
	
}
