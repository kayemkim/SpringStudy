package nephilim.study.spring.ch06.practice;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;
import nephilim.study.spring.ch06.practice.service.BlogService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BlogServiceTest {
	
	@Autowired
	private BlogService service;
	
	private List<Blog> blogs;
	
	@Before
	public void insertMembers() throws Exception{
		
		blogs = prepareBlogs();
		for ( Blog blog:blogs) {
			service.add(blog);
		}
	}

	private List<Blog> prepareBlogs() throws MalformedURLException {
		Blog blog1 = new Blog();
		blog1.setName("blog1");
		blog1.setAddress(new URL("http://www.blog1.com"));
		blog1.setEmail("blog1-me@blog1.com");
		
		Blog blog2 = new Blog();
		blog2.setName("blog2");
		blog2.setAddress(new URL("http://www.blog2.com"));
		blog2.setEmail("blog2-me@blog2.com");
	
		/*
		Post post2_1 = new Post();
		post2_1.setTitle("blog2-01.title");
		post2_1.setContent("blog2-01.content");
		post2_1.setCreated(new Date());
		
		Post post2_2 = new Post();
		post2_2.setTitle("blog2-02.title");
		post2_2.setContent("blog2-02.content");
		post2_2.setCreated(new Date());
		
		List<Post> postsInBlog2 = Arrays.asList(new Post[]{post2_1, post2_2});
		blog2.setPosts(postsInBlog2);
		*/
		
		Blog blog3 = new Blog();
		blog3.setName("blog3");
		blog3.setAddress(new URL("http://www.blog3.com"));
		blog3.setEmail("blog3-me@blog3.com");
		
		List<Blog> blogs= Arrays.asList(new Blog[]{blog1, blog2, blog3});
		return blogs;
	}
	
	@After
	public void deleteBlogs() {
		service.deleteAll();
	}
	
	@Test
	public void getAllThreeBlogs() {
		List<Blog> blogs= service.getAll();
		assertTrue( 3 == blogs.size());
	}
	
	@Test
	public void addPost() {
		List<Blog> blogs = service.getAll();
		for (int blogIndex=0; blogIndex < blogs.size(); blogIndex++ ){
			for (int postIndex=0; postIndex < blogIndex; postIndex++) {
				Post post = new Post();
				post.setTitle(String.format("post-%02d",postIndex));
				post.setContent("....");
				post.setCreated(new Date());
				
				service.addPost(blogs.get(blogIndex), post);
			}
		}
		
		for (int blogIndex=0; blogIndex < blogs.size(); blogIndex++) {
			int expectedSize = blogIndex;
			Blog blog = service.get(blogs.get(blogIndex).getId());
			List<Post> blogPosts = blog.getPosts();
			System.out.println(blogPosts);
			assertTrue(expectedSize == blogPosts.size());
		}
	}
}
