package nephilim.study.spring.ch06.practice.service;

import java.util.List;

import nephilim.study.spring.ch06.practice.dao.BlogDao;
import nephilim.study.spring.ch06.practice.dao.PostDao;
import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;

public class BlogServiceImpl implements BlogService {
	
	BlogDao blogDao;
	
	PostDao postDao;


	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	
	/* (non-Javadoc)
	 * @see nephilim.study.spring.ch06.practice.service.BlogService#getAll()
	 */
	public List<Blog> getAll() {
		List<Blog> blogs = blogDao.getAll();
		for (Blog blog:blogs) {
			List<Post> posts = postDao.get(blog);
			blog.setPosts(posts);
		}
		return blogs;
	}
	
	/* (non-Javadoc)
	 * @see nephilim.study.spring.ch06.practice.service.BlogService#get(int)
	 */
	public Blog get(int id) {
		Blog blog = blogDao.get(id);
		List<Post> posts = postDao.get(blog);
		blog.setPosts(posts);
		return blog;
	}

	/* (non-Javadoc)
	 * @see nephilim.study.spring.ch06.practice.service.BlogService#add(nephilim.study.spring.ch06.practice.model.Blog)
	 */
	public void add(Blog blog) {
		if (blog.getId() != Blog.NO_ID ) {
			throw new IllegalStateException(
					"이미 ID가 존재하는 객체는 추가할 수 없습니다");
		}
		
		final int blogId = blogDao.createId();  
		blog.setId(blogId);
		blogDao.add(blog);
		
		List<Post> posts = blog.getPosts(); 
		if ( posts != null ) {
			for ( Post post:posts ) {
				addPost(blog, post);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see nephilim.study.spring.ch06.practice.service.BlogService#addPost(nephilim.study.spring.ch06.practice.model.Blog, nephilim.study.spring.ch06.practice.model.Post)
	 */
	public void addPost(Blog blog, Post post) {
		if (post.getId() != Post.NO_ID ) {
			throw new IllegalStateException(
					"이미 ID가 존재하는 객체는 추가할 수 없습니다");
		}
		final int postId = postDao.createId();
		post.setId(postId);
		postDao.add(blog, post);
	}

	/* (non-Javadoc)
	 * @see nephilim.study.spring.ch06.practice.service.BlogService#deleteAll()
	 */
	public void deleteAll() {
		List<Blog> blogs = blogDao.getAll();
		
		for (Blog blog:blogs) {
			postDao.deleteAllPost(blog);
		}
		
		blogDao.deleteAll();
	}

}
