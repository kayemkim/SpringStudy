package nephilim.study.spring.ch06.practice.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;

public interface BlogService {

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public abstract List<Blog> getAll();

	@Transactional(readOnly=true)
	public abstract Blog get(int id);

	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public abstract void add(Blog blog);

	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public abstract void addPost(Blog blog, Post post);
	
	public abstract void deleteAll();

}