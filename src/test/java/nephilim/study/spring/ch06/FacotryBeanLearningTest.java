package nephilim.study.spring.ch06;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FacotryBeanLearningTest {
	
	@Autowired
	ApplicationContext context;
	
	@Test
	public void getMessageFromFactoryBean()  {
		Object message =  context.getBean("message");
		assertTrue(Message.class.isInstance(message));
	}
	
	@Test
	public void getFatoryBean() {
		Object messageFactoryBean =  context.getBean("&message");
		assertTrue(MessageFactoryBean.class.isInstance(messageFactoryBean));
	}
}
