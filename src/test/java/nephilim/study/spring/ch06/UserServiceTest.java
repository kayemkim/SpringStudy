package nephilim.study.spring.ch06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:nephilim/study/spring/ch06/applicationContext.xml")
public class UserServiceTest {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired 
	DataSource dataSource;
	
	List<User> users;
	UserDao userDao;
	
	@Before
	public void setUp() {
		userDao = userService.dao;
		users = Arrays.asList(
					new User("id01", "±è°æ¹Î", "rlarudals", Level.BASIC, 20 , 20 ),
					new User("id02", "±èÀ±¿ì", "rladbsdn", Level.BASIC, 10, 19 ),
					new User("id03", "ÀÌµ¿¿í", "dlehddnr", Level.SILVER, 1, 30 )
				);
	}
	
	@Test
	public void beanInjection() {
		assertNotNull(userService);
	}
	
	@Test
	public void upgradeLevels() throws Exception{
		userDao.deleteAll();
		for(User user:users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevel(users.get(0), Level.SILVER);
		checkLevel(users.get(1), Level.BASIC);
		checkLevel(users.get(2), Level.GOLD);
		
	}
	
	
	

	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertEquals("", userUpdate.getLevel(), expectedLevel);
	}
	
	@Test
	public void upgradeAllOrNothing() throws Exception {
		
		List<Level> levelsBefore = 
			Arrays.asList( users.get(0).getLevel(), users.get(1).getLevel());
		
		UserServiceForTest testUserService = new UserServiceForTest(users.get(2).getId());
		testUserService.setUserDao(userDao);
		//testUserService.setDataSource(dataSource);
		
		userDao.deleteAll();
		for(User user:users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();
			fail("TUSException not occurred!");
		} catch (TestUserServiceExcetion tuse) {
			// ok
		}
		
		users = userDao.getAll();
		assertEquals(levelsBefore.get(0), users.get(0).getLevel());
		assertEquals(levelsBefore.get(1), users.get(1).getLevel());		
	}
	
	@Test
	public void upgradeLevelsWithMock() {
		
	}
	
	/**
	 * Æ¯Á¤ idÀÇ »ç¿ëÀÚ ugrader½Ã ¿¹¿Ü¸¦ ¹ß»ý½ÃÅ´
	 *
	 */
	class UserServiceForTest extends UserServiceImpl {
		private String id;
		
		public UserServiceForTest(String id) {
			this.id = id;
		}
		
		public void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) throw new TestUserServiceExcetion();
			super.upgradeLevel(user);
		}
	}
	class TestUserServiceExcetion extends RuntimeException {}
}
