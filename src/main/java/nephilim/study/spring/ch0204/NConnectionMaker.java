package nephilim.study.spring.ch0204;

import java.sql.Connection;

public class NConnectionMaker implements ConnectionMaker {

	public Connection makeConnection() {
		System.out.println("Connection-Null created");
		return null;		
	}
}
		
