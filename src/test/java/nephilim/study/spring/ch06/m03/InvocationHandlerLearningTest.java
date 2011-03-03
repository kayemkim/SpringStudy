package nephilim.study.spring.ch06.m03;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InvocationHandlerLearningTest {
	
	@Test
	public void proxiedTimeMeasure() {
		List<String> list = new ArrayList<String>();
		TimeMeasureHandler handler = new TimeMeasureHandler();
		handler.setTarget(list);
		
		List<String> proxiedList = (List<String>)Proxy.newProxyInstance( 
				List.class.getClassLoader(), 
				new Class[]{List.class}, 
				handler);
		
		// 콘솔 출력 결과를 확인
		proxiedList.add("A");
		proxiedList.add("B");
		proxiedList.add("C");
		proxiedList.add("D");
	}
}
