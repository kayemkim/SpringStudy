package nephilim.study.spring.ch06.m04;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class ProxyFactoryBeanLearningTest {
	
	@Test
	public void proxiedWithUpperCaseAdvice() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		pfBean.addAdvice(new UppercaseAdvice());
		
		Hello proxiedHello = (Hello)pfBean.getObject();
		
		System.out.println(proxiedHello.sayHello("meeeeee1"));
	}
	
	@Test
	public void proxiedWithUpperCaseAndTitleDecoAdvice() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		pfBean.addAdvice(new UppercaseAdvice());
		pfBean.addAdvice(new TitleDecoAdvice());
		
		Hello proxiedHello = (Hello)pfBean.getObject();
		
		System.out.println(proxiedHello.sayHello("meeeeee3"));
	}
	
	@Test
	public void proxiedWithTitleDecoAndUpperCaseAdvice() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		pfBean.addAdvice(new TitleDecoAdvice());
		pfBean.addAdvice(new UppercaseAdvice());
		
		Hello proxiedHello = (Hello)pfBean.getObject();
		
		System.out.println(proxiedHello.sayHello("meeeeee3"));
	}
	
	@Test
	public void proxiedWithUpperCaseAdvisor() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		// �����̽� ����(MethodInterceptor)
		Advice uppercaseAdvice = new UppercaseAdvice();
		
		// ����Ʈ �� �߰�
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayHello*");
		
		// �������� ���� 
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, uppercaseAdvice);
		pfBean.addAdvisor(advisor);
		
		Hello proxiedHello = (Hello)pfBean.getObject();
		
		System.out.println(proxiedHello.sayHello("meeeeee4"));
	}
}
