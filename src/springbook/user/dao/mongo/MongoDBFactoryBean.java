package springbook.user.dao.mongo;

import org.springframework.beans.factory.FactoryBean;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoDBFactoryBean implements FactoryBean<DB>{
	
	// Mongo 객체 (싱글턴)
	private Mongo mongo;
	// MongoDB 데이터베이스 이름
	private String dbname;
	
	@Override
	public DB getObject() throws Exception {
		return mongo.getDB(dbname);
	}

	@Override
	public Class<?> getObjectType() {
		return DB.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}
	
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
}
