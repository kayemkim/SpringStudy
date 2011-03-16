package nephilim.study.spring.ch06;

import java.util.List;

public class UserServiceImpl implements UserService {
	
	UserDao dao;
	
	public void setUserDao(UserDao dao){
		this.dao = dao;
	}

	public void add(User user) {
		dao.add(user);
	}
	
	public void upgradeLevels() {
		List<User> users = dao.getAll();
		for(User user:users) {
			upgradeLevel(user);
		}
	}
	
	public void upgradeLevel(User user) {
		if (user.upgradeLevel()) {
			dao.update(user);
		}
	}
}
