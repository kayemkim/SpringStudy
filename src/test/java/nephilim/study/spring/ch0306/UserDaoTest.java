package nephilim.study.spring.ch0306;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:nephilim/study/spring/ch0306/applicationContext.xml")
public class UserDaoTest {
	
	@Autowired
	ApplicationContext context;
	
	@Before
	public void confirmAppContext() {
		assertTrue( context != null);
		
	}
	
	@Test
	public void 사용자조회() {
		UserDao dao = context.getBean("userDao", UserDao.class);
		List<User> users = dao.getAll();
		
		
		System.out.println(users);
		assertTrue( 3 == users.size());
		
	}
}
