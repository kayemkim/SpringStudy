package nephilim.study.spring.ch05;

import static org.junit.Assert.*;

import org.junit.Test;


public class LevelTest {
	
	@Test
	public void valueOf() {
		assertEquals( "Enum.valueOf의 디폴트 구현은 String을 Level로 변경함", 
				Level.GOLD, Level.valueOf("GOLD"));
	}
	
	@Test
	public void ordinal() {
		assertEquals("GOLD의 순서는 3번째이므로 ordianal이 2이어야 함",
				2,
				Level.GOLD.ordinal());
	}
	
	

}
