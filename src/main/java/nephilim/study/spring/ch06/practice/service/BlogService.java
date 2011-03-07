package nephilim.study.spring.ch06.practice.service;

import java.util.List;

import nephilim.study.spring.ch06.practice.dao.BlogDao;
import nephilim.study.spring.ch06.practice.dao.PostDao;
import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;

public class BlogService {
	
	BlogDao blogDao;
	PostDao postDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	
	public List<Blog> getAll() {
		return blogDao.getAll();
	}
	
	public Blog get(int id) {
		Blog blog = blogDao.get(id);
		List<Post> posts = postDao.get(blog);
		blog.setPosts(posts);
		return blog;
	}

	public void add(Blog blog) {
		blogDao.add(blog);	
	}
	
	public void addPost(Blog blog, Post post) {
		postDao.add(blog, post);
	}

	public void deleteAll() {
		List<Blog> blogs = blogDao.getAll();
		
		for (Blog blog:blogs) {
			postDao.deleteAllPost(blog);
		}
		
		blogDao.deleteAll();
	}

}
