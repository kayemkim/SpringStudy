package nephilim.study.spring.ch0305;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class CalcSumTest {
	
	@Test
	public void sumAllNumbers() throws IOException {
		Calculator calculator = new Calculator();
		int sum = calculator.calcSum( getClass().getResource("numbers.txt").getPath() );
		assertEquals("���Ͽ��� ���� ���� ���� 150���� ��", (10+20+30+40+50),sum);
	}
}
