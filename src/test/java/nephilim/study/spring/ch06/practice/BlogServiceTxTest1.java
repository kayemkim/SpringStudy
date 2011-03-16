package nephilim.study.spring.ch06.practice;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import nephilim.study.spring.ch06.practice.dao.BlogDao;
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
public class BlogServiceTxTest1 {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogService testBlogService;
	
	@Autowired
	private BlogDao blogDao;
	
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
		blogService.deleteAll();
		blogs = createBlogs();
		for ( Blog blog:blogs) {
			blogService.add(blog);
		}
	}
	
	@Test
	public void getAllThreeBlogs() {
		List<Blog> blogs= blogService.getAll();
		assertTrue("총 블로그 수는 3건임", 
				3 == blogs.size());
		
		Blog keyemBlog = blogs.get(1);
		assertTrue("kayem 블로그에는 post가 2건 존재해야함",
				2 == keyemBlog.getPosts().size() );
	}
	
	@Test(expected = IllegalStateException.class)
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
		
		// id가 이미 부여되어 있어
		// insert 직전 예외발생
		Post post2 = new Post();
		post2.setId(1);		
		post2.setTitle("post02.title");
		post2.setContent("nephilim is not so bad");
		post2.setCreated(new Date());
		
		List<Post> posts = Arrays.asList(new Post[]{post1, post2});
		newBlog.setPosts(posts);
		
		try{
			blogService.add(newBlog);
		} catch (IllegalStateException e) {
			List<Blog> blogs = blogService.getAll();
			assertTrue("Tx가 적용되어 블로그 사이즈는 기존과 같아야 함",
					blogs.size() == createBlogs().size()); 
			throw e;
		}
		
	}

	
	@Test
	@Transactional(readOnly=true)
	public void readOnlyTest() {
		
	}
	
}
