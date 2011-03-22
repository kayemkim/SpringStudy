package nephilim.study.spring.ch07.practice.service;

import java.util.List;

import nephilim.study.spring.ch07.practice.model.Blog;
import nephilim.study.spring.ch07.practice.model.Post;

public interface BlogService {

	public abstract List<Blog> getAll();

	public abstract Blog get(int id);

	public abstract void add(Blog blog);

	public abstract void addPost(Blog blog, Post post);
	
	public abstract void deleteAll();

}