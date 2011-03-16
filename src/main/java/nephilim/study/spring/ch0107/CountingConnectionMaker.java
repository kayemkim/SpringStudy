package nephilim.study.spring.ch0107;

import java.sql.Connection;

public class CountingConnectionMaker implements ConnectionMaker {
	int count;
	private ConnectionMaker actualConnectionMaker; 
	
	public CountingConnectionMaker(ConnectionMaker actualConnectionMaker) {
		this.actualConnectionMaker = actualConnectionMaker;
	}
	
	public int getCounter() {
		return this.count;
	}

	public Connection makeConnection() {
		count++;
		return actualConnectionMaker.makeConnection();
	}

}
