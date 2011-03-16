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
		
		// default value�� ��ȯ�Ѵ�.
		assertTrue( list.get(0) == null);	
	}
	
	@Test
	public void verifyCallGetOnList() {
		List<Integer> list = mock(List.class);

		list.get(0);
		
		// get(0) ȣ�� ���θ� Ȯ��
		verify(list).get(0);
	}
	
	@Test 
	public void stubIntegerList() {
		List<Integer> list = mock(List.class);
		when(list.get(0)).thenReturn(10);
		
		// ����Ʈ ���� ��ȯ�� �ˤ���
		assertTrue(list.get(0) == 10);
		assertTrue(list.get(1) == null);
		
		// 0ȣ�� ���θ� Ȯ�� 
		verify(list).get(0);
	}
	
	@Test(expected = MockitoException.class)
	public void createMockedString() {
		/*
		 * final, anonymous, primitive types�� 
		 * mock�� ������ �� ����.
		 * String final 
		 */
		mock(String.class);
	}
	
}
