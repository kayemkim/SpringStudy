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
					new User("id01", "����", "kkm", Level.BASIC, 20 , 20 ),
					new User("id02", "������", "kyw", Level.BASIC, 10, 19 ),
					new User("id03", "�̵���", "lde", Level.SILVER, 1, 20 ),
					new User("id04", "�ڹ���", "pmj", Level.SILVER, 1, 30 )
				);
	}
	
	@Test
	public void beanInjection() {
		assertNotNull(context);
	}
		
	@Test
	public void upgradeAllOrNothingWithTxProxy() throws Exception {
		// ���� ���׷��̵� �۾� ���� �� ���� ����
		List<Level> levelsBefore = 
			Arrays.asList( 
					users.get(0).getLevel(), 
					users.get(1).getLevel(), 
					users.get(2).getLevel(), 
					users.get(3).getLevel());
		
		// DB �غ� �۾�
		UserDao userDao = (UserDao)context.getBean("userDao");
		userDao.deleteAll();
		for(User user:users) userDao.add(user);

		// 3��° ������� ���ܰ� �߻��ϵ��� Service ����
		UserServiceForTest testUserService = new UserServiceForTest(users.get(2).getId());
		testUserService.setUserDao(userDao);

		// TxUserService�� proxy�� target ����
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
		
		// Tx.rollback()�� �̷����
		// upgradeLevels ������ level�� ������ ���������˻� 
		users = userDao.getAll();
		for (int inx=0; inx<users.size(); inx++) {
			System.out.println(users.get(inx));
			assertEquals(levelsBefore.get(inx), users.get(inx).getLevel());
		}		
	}
	
	@Test
	public void upgradeAllOrNothingWithTxAndTimeMeasureProxy() throws Exception{
		// ���� ���׷��̵� �۾� ���� �� ���� ����
		List<Level> levelsBefore = 
			Arrays.asList( 
					users.get(0).getLevel(), 
					users.get(1).getLevel(), 
					users.get(2).getLevel(), 
					users.get(3).getLevel());
		
		// DB �غ� �۾�
		UserDao userDao = (UserDao)context.getBean("userDao");
		userDao.deleteAll();
		for(User user:users) userDao.add(user);

		// 3��° ������� ���ܰ� �߻��ϵ��� Service ����
		UserServiceForTest testUserService = new UserServiceForTest(users.get(2).getId());
		testUserService.setUserDao(userDao);

		/*
		 * Tx + TimeMeasure Proxy ���� 
		 */
		
		// TxUserService(proxy)�� target ����
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
		
		// Tx.rollback()�� �̷����
		// upgradeLevels ������ level�� ������ ���������˻� 
		users = userDao.getAll();
		for (int inx=0; inx<users.size(); inx++) {
			System.out.println(users.get(inx));
			assertEquals(levelsBefore.get(inx), users.get(inx).getLevel());
		}	
		// Tx + TimeMeasure
		
		// ���� �� ������ ����
		
	}
	
	/**
	 * Ư�� id�� ����� ugradeLevel�� TestUserServiceExcetion���ܸ� �߻���Ŵ
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
