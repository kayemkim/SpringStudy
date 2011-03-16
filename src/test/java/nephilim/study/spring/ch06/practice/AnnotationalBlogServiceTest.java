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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AnnotationalBlogServiceTest {
	
	@Autowired
	private BlogService blogService;
	
	private List<Blog> blogs;
	
	private List<Blog> createBlogs() throws MalformedURLException {
		Blog blog1 = new Blog();
		blog1.setName("nephilim blog");
		blog1.setAddress(new URL("http://nephilim.blog1.com"));
		blog1.setEmail("donmakemepm@blog1.com");
		
		Blog blog2 = new Blog();
		blog2.setName("kayem blog");
		blog2.setAddress(new URL("http://kayem.blog2.com"));
		blog2.setEmail("stomachache-kim@blog2.com");
		
		Post post2_1 = new Post();
		post2_1.setTitle("blog2-01.title");
		post2_1.setContent("nephilim is bad");
		post2_1.setCreated(new Date());
		
		Post post2_2 = new Post();
		post2_2.setTitle("blog2-02.title");
		post2_2.setContent("nephilim is not so bad");
		post2_2.setCreated(new Date());
		
		List<Post> postsInBlog2 = Arrays.asList(new Post[]{post2_1, post2_2});
		blog2.setPosts(postsInBlog2);
		
		Blog blog3 = new Blog();
		blog3.setName("yunuu blog");
		blog3.setAddress(new URL("http://yunu.blog3.com"));
		blog3.setEmail("findyunu@blog3.com");
		
		List<Blog> blogs= Arrays.asList(new Blog[]{blog1, blog2, blog3});
		return blogs;
	}
	

	/**
	 * 테스트의 전/후 처리 또한 트랜잭션 경계에 포함됨
	 */
	@Before
	public void deleteAllData() {
		blogService.deleteAll();
		
		List<Blog> currentBlogs = blogService.getAll();
		assertTrue("블로그 삭제 후 크기는 0이어야함", 
				currentBlogs.size() == 0);
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void addPost() 
	throws Exception {
		List<Blog> prevBlogs = blogService.getAll();
		
		Blog newBlog = new Blog();
		newBlog.setName("new blog");
		newBlog.setAddress(new URL("http://new.blog.com"));
		newBlog.setEmail("imfresh@blog.com");
		
		Post post1 = new Post();
		post1.setTitle("post01.title");
		post1.setContent("nephilim is bad");
		post1.setCreated(new Date());
		
		Post post2 = new Post();
		post2.setTitle("post02.title");
		post2.setContent("nephilim is not so bad");
		post2.setCreated(new Date());
		
		List<Post> posts = Arrays.asList(new Post[]{post1, post2});
		newBlog.setPosts(posts);
		
		blogService.add(newBlog);
		
		List<Blog> currentBlogs = blogService.getAll();
		
		assertTrue("블로그 추가 후 이전 크기보다 1증가해야함", 
				(prevBlogs.size() + 1) == currentBlogs.size());
		
	}

	
	
	
}
