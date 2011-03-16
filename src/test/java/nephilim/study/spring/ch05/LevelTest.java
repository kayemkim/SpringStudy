package nephilim.study.spring.ch05;

import static org.junit.Assert.*;

import org.junit.Test;


public class LevelTest {
	
	@Test
	public void valueOf() {
		assertEquals( "Enum.valueOf�� ����Ʈ ������ String�� Level�� ������", 
				Level.GOLD, Level.valueOf("GOLD"));
	}
	
	@Test
	public void ordinal() {
		assertEquals("GOLD�� ������ 3��°�̹Ƿ� ordianal�� 2�̾�� ��",
				2,
				Level.GOLD.ordinal());
	}
	
	

}
