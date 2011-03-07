package nephilim.study.spring.ch06.practice.model;

import java.util.Date;

public class Post {
	private String title;
	private String content;
	private Date created;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	
}
