package nephilim.study.spring.ch06.practice.model;

import java.net.URL;
import java.util.List;

public class Blog {
	public static int NO_ID = -1;
	
	private int id = NO_ID;
	private String name;
	private URL address;
	private String email;
	private List<Post> posts;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public URL getAddress() {
		return address;
	}
	
	public void setAddress(URL address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
