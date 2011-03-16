package nephilim.study.spring.ch06.practice;

import java.util.List;

import org.junit.Test;

public class Java5Test {
	
	@Test(expected = NullPointerException.class)
	public void enhancedForLoop() {
		List<Integer> ints = null;
		int loopCount = 0;
		for(int inx:ints) {
			loopCount ++;
		}
	}
}
