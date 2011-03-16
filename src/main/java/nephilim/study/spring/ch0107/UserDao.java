package nephilim.study.spring.ch0107;
import java.sql.Connection;

public class UserDao {
	Connection connection;
	
	public UserDao() {
		
	}

	public void setConnectionMaker(ConnectionMaker	 connMaker) {
		connection = connMaker.makeConnection();		
	}
}
