package springbook.user.dao.mongo;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoMongodb implements UserDao {
	private DB db;
	
	public void setDb(DB db) {
		this.db = db;
	}
	
	@Override
	public void add(User user) {
		try {
			db.requestStart();
			DBCollection collection = db.getCollection("players");
			DBObject doc = new BasicDBObject();
			doc.put("id", user.getId());
			doc.put("name", user.getName());
			doc.put("password", user.getPassword());
			doc.put("email", user.getEmail());
			//doc.put("level", user.getLevel());
			doc.put("login", user.getLogin());
			doc.put("recommend", user.getRecommend());
			collection.insert(doc);
		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			db.requestDone();
		}
	}

	@Override
	public User get(String id) {
		User user = new User();
		try {
			db.requestStart();
			DBCollection collection = db.getCollection("players");
			DBObject doc = new BasicDBObject();
			doc.put("id", id);
			DBObject dbObject = collection.findOne(doc);
			if(dbObject == null) {
				return null;
			}
			user.setId(dbObject.get("id").toString());
			user.setName(dbObject.get("name").toString());
			user.setPassword(dbObject.get("password").toString());
			user.setEmail(dbObject.get("email").toString());
			user.setLogin(Integer.parseInt(dbObject.get("login").toString()));
			user.setRecommend(Integer.parseInt(dbObject.get("recommend").toString()));
		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			db.requestDone();
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		try {
			db.requestStart();
			DBCollection collection = db.getCollection("players");
			collection.drop();
		} catch(MongoException e) {
			e.printStackTrace();
		} finally {
			db.requestDone();
		}
	}

	@Override
	public int getCount() {
		int count = 0;
		try {
			db.requestStart();
			DBCollection collection = db.getCollection("players");
			// long -> int Â©·Á¸ÔÀ½
			count = (int) collection.getCount();
		} catch(MongoException e) {
			e.printStackTrace();
		} finally {
			db.requestDone();
		}
		return count;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

}
