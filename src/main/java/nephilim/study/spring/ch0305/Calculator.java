package nephilim.study.spring.ch0305;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public int calcSum(String path) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(path));
			Integer sum = 0;		// autoboxing
			String line = null;
			
			while((line = br.readLine()) != null ) { 
				sum += Integer.valueOf(line);
			}
			return sum;		// position
		}
		catch ( IOException ioe) {
			System.out.println( ioe.getMessage() );
			throw ioe;
		} 
		finally {
			if (br != null) {
				try { br.close(); } 
				catch (IOException ioe) { System.out.println(ioe.getMessage()); }
			}
		}
	}
}
