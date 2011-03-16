package nephilim.study.spring.ch06.m05;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultAdvisorAutoProxyCreatorLearningTest {
	
	@Autowired
	ApplicationContext context;
	
	@Test
	public void proxiedWithUpperCaseAdvice() {
		TextSpeech speech = (TextSpeech)context.getBean("textSpeech");
		String result = speech.sayStoredText();
		System.out.println(result);
	}
	
}
