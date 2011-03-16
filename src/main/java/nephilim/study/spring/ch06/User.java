package nephilim.study.spring.ch06;

public class User {
	private String id;
	private String name;
	private String password;
	private Level level;
	private int login;
	private int recommend;
	
	public User() {
	}

	public User(String id, String name, String password, Level level, int login, int recommend) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	@Override
	public String toString() {
		return String.format("User[id= %s, name= %s, password= %s, level= %s]",
				id,name,password, level);
	}

	public boolean upgradeLevel() {
		boolean isUpgraded = false;
		
		switch (level) {
			case GOLD: break;
			case SILVER: 
				if( recommend >= 30 ) {
					level = Level.GOLD;
					isUpgraded = true;
				}
				break;
			case BASIC: 
				if( recommend >=20 ){
					level = Level.SILVER;
					isUpgraded = true;
				}
				break;
		}
		
		return isUpgraded;
	}


	
}
