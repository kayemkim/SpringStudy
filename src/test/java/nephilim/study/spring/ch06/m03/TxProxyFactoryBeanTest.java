package nephilim.study.spring.ch06.m03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import nephilim.study.spring.ch06.Level;
import nephilim.study.spring.ch06.User;
import nephilim.study.spring.ch06.UserDao;
import nephilim.study.spring.ch06.UserService;
import nephilim.study.spring.ch06.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TxProxyFactoryBeanTest {
	
	@Autowired
	ApplicationContext context;
	
	
	List<User> users;
	
	@Before
	public void setUp() {
		users = Arrays.asList(
					new User("id01", "김경민", "kkm", Level.BASIC, 20 , 20 ),
					new User("id02", "김윤우", "kyw", Level.BASIC, 10, 19 ),
					new User("id03", "이동욱", "lde", Level.SILVER, 1, 20 ),
					new User("id04", "박민정", "pmj", Level.SILVER, 1, 30 )
				);
	}
	
	@Test
	public void beanInjection() {
		assertNotNull(context);
	}
		
	@Test
	public void upgradeAllOrNothingWithTxProxy() throws Exception {
		// 레벨 업그레이드 작업 수행 전 상태 보관
		List<Level> levelsBefore = 
			Arrays.asList( 
					users.get(0).getLevel(), 
					users.get(1).getLevel(), 
					users.get(2).getLevel(), 
					users.get(3).getLevel());
		
		// DB 준비 작업
		UserDao userDao = (UserDao)context.getBean("userDao");
		userDao.deleteAll();
		for(User user:users) userDao.add(user);

		// 3번째 멤버에서 예외가 발생하도록 Service 설정
		UserServiceForTest testUserService = new UserServiceForTest(users.get(2).getId());
		testUserService.setUserDao(userDao);

		// TxUserService로 proxy의 target 변경
		TxProxyFactoryBean txProxyFactoryBean = 
			context.getBean("&userServiceTx", TxProxyFactoryBean.class);
		txProxyFactoryBean.setTarget(testUserService);
		UserService txUserService = (UserService)txProxyFactoryBean.getObject(); 
		
		
		try {
			txUserService.upgradeLevels();
			fail("TUSException not occurred!");
		} catch (TestUserServiceExcetion tuse) {
			// ok
		}
		
		// Tx.rollback()이 이루어져
		// upgradeLevels 수행전 level과 동일한 상태인지검사 
		users = userDao.getAll();
		for (int inx=0; inx<users.size(); inx++) {
			System.out.println(users.get(inx));
			assertEquals(levelsBefore.get(inx), users.get(inx).getLevel());
		}		
	}
	
	@Test
	public void upgradeAllOrNothingWithTxAndTimeMeasureProxy() throws Exception{
		// 레벨 업그레이드 작업 수행 전 상태 보관
		List<Level> levelsBefore = 
			Arrays.asList( 
					users.get(0).getLevel(), 
					users.get(1).getLevel(), 
					users.get(2).getLevel(), 
					users.get(3).getLevel());
		
		// DB 준비 작업
		UserDao userDao = (UserDao)context.getBean("userDao");
		userDao.deleteAll();
		for(User user:users) userDao.add(user);

		// 3번째 멤버에서 예외가 발생하도록 Service 설정
		UserServiceForTest testUserService = new UserServiceForTest(users.get(2).getId());
		testUserService.setUserDao(userDao);

		/*
		 * Tx + TimeMeasure Proxy 생성 
		 */
		
		// TxUserService(proxy)의 target 변경
		TxProxyFactoryBean txProxyFactoryBean = 
			context.getBean("&userServiceTx", TxProxyFactoryBean.class);
		txProxyFactoryBean.setTarget(testUserService);
		UserService txUserService = (UserService)txProxyFactoryBean.getObject(); 
		
		//
		TimeMeasureProxyFactoryBean txAndTimeMeasureProxyFactoryBean = 
			context.getBean("&userServiceTxAndTimeMeasure", TimeMeasureProxyFactoryBean.class);
		txAndTimeMeasureProxyFactoryBean.setTarget(txUserService);
		UserService txAndTimeUserService = (UserService)txAndTimeMeasureProxyFactoryBean.getObject(); 
		
		//UserService txAndTimeUserService = context.getBean("userServiceTxAndTimeMeasure", UserService.class);
		
		try {
			txAndTimeUserService.upgradeLevels();
			fail("TUSException not occurred!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Tx.rollback()이 이루어져
		// upgradeLevels 수행전 level과 동일한 상태인지검사 
		users = userDao.getAll();
		for (int inx=0; inx<users.size(); inx++) {
			System.out.println(users.get(inx));
			assertEquals(levelsBefore.get(inx), users.get(inx).getLevel());
		}	
		// Tx + TimeMeasure
		
		// 실행 후 검증은 생략
		
	}
	
	/**
	 * 특정 id의 사용자 ugradeLevel시 TestUserServiceExcetion예외를 발생시킴
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
