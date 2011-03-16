package nephilim.study.spring.ch06.m02;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

public class MockitoLearningTest {

	@Test 
	public void createGeneralMock() {
		List<Integer> list = mock(List.class);
		
		// default value를 반환한다.
		assertTrue( list.get(0) == null);	
	}
	
	@Test
	public void verifyCallGetOnList() {
		List<Integer> list = mock(List.class);

		list.get(0);
		
		// get(0) 호출 여부를 확인
		verify(list).get(0);
	}
	
	@Test 
	public void stubIntegerList() {
		List<Integer> list = mock(List.class);
		when(list.get(0)).thenReturn(10);
		
		// 리스트 원소 반환을 검ㅈ으
		assertTrue(list.get(0) == 10);
		assertTrue(list.get(1) == null);
		
		// 0호출 여부를 확인 
		verify(list).get(0);
	}
	
	@Test(expected = MockitoException.class)
	public void createMockedString() {
		/*
		 * final, anonymous, primitive types는 
		 * mock을 생성할 수 없다.
		 * String final 
		 */
		mock(String.class);
	}
	
}
