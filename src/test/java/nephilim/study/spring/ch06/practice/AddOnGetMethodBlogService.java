package nephilim.study.spring.ch06.practice;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;
import nephilim.study.spring.ch06.practice.service.BlogServiceImpl;

public class AddOnGetMethodBlogService extends BlogServiceImpl {
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<Blog> getAll(){
		System.out.println("UpdateOnGetMethodBlogService.getAll()-Delete");
		/*
		URL url = null;
		try {
			url = new URL("http://new.blog.com");
		} catch (MalformedURLException e) { throw new IllegalArgumentException(); }		
		
		Blog newBlog = new Blog();
		newBlog.setName("new blog");
		newBlog.setAddress(url);
		newBlog.setEmail("imfresh@blog.com");
		*/
		
		super.deleteAll();
		return null;
	}

	public Blog get(int id) {
		super.deleteAll();
		return null;
	}

	public void add(Blog blog) {
		throw new UnsupportedOperationException();
	}

	public void addPost(Blog blog, Post post) {
		throw new UnsupportedOperationException();
	}

	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

}
