package nephilim.study.spring.ch07.practice;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import nephilim.study.spring.ch07.practice.model.Blog;
import nephilim.study.spring.ch07.practice.model.Post;
import nephilim.study.spring.ch07.practice.service.BlogService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BlogServiceTest {
	
	@Autowired
	private BlogService service;
	
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
	
	@Before
	public void insertBlogs() throws Exception{
		service.deleteAll();
		blogs = createBlogs();
		for ( Blog blog:blogs) {
			service.add(blog);
		}
	}
	
	@Test
	@Transactional(value="txManager")
	public void getAllThreeBlogs() {
		List<Blog> blogs= service.getAll();
		assertTrue("�� ��α� ���� 3����", 
				3 == blogs.size());
		
		Blog keyemBlog = blogs.get(1);
		assertTrue("kayem ��α׿��� post�� 2�� �����ؾ���",
				2 == keyemBlog.getPosts().size() );
	}
	
	
	@Test(expected = IllegalStateException.class)
	@Transactional(value="txManager")
	public void addPost() 
	throws Exception {
		Blog newBlog = new Blog();
		newBlog.setName("new blog");
		newBlog.setAddress(new URL("http://new.blog.com"));
		newBlog.setEmail("imfresh@blog.com");
		
		Post post1 = new Post();
		post1.setTitle("post01.title");
		post1.setContent("nephilim is bad");
		post1.setCreated(new Date());
		
		// id�� �̹� �ο��Ǿ� �־�
		// insert ���� ���ܹ߻�
		Post post2 = new Post();
		post2.setId(1);		
		post2.setTitle("post02.title");
		post2.setContent("nephilim is not so bad");
		post2.setCreated(new Date());
		
		List<Post> posts = Arrays.asList(new Post[]{post1, post2});
		newBlog.setPosts(posts);
		
		try{
			service.add(newBlog);
		} catch (IllegalStateException e) {
			List<Blog> blogs = service.getAll();
			assertTrue("Tx�� ������� �ʾ� ��α� ������� ���� ���� 1�� ���ƾ� ��",
					blogs.size() == createBlogs().size() + 1); 
			throw e;
		}
		
	}
}
