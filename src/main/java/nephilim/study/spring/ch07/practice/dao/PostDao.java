package nephilim.study.spring.ch07.practice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import nephilim.study.spring.ch07.practice.model.Blog;
import nephilim.study.spring.ch07.practice.model.Post;
import nephilim.study.spring.ch07.practice.sqlservice.SqlService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PostDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private SqlService sqlService;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Post> postRowMapper = new RowMapper<Post>(){
		public Post mapRow(ResultSet rs, int rowNum) 
		throws SQLException {
			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setCreated(rs.getTimestamp("created"));
			return post;
		}
	};

	public int createId() {
		return jdbcTemplate.queryForInt(
				sqlService.getSql("post.createId"));
	}
	
	public Post get(Blog blog, int id) {
		return jdbcTemplate.queryForObject(
				sqlService.getSql("post.getById"),
				new Integer[] {id},
				postRowMapper);
	}
	
	public List<Post> get(Blog blog) {
		List<Post> blogs = null;
		
		blogs = jdbcTemplate.query(
					sqlService.getSql("post.getAllInBlog"),
					new Integer[]{ blog.getId() }, 
					postRowMapper);
		
		return blogs;
	}
	
	public void add(Blog blog, Post post) {
		Timestamp created = new Timestamp(post.getCreated().getTime());
		jdbcTemplate.update(
				"insert into post(id, blog_id, title, content, created) " +
				"values(?,?,?,?,?)",
				post.getId(),
				blog.getId(),
				post.getTitle(),
				post.getContent(),
				created
				);
	}
	
	public void deleteAllPost(Blog blog) {
		jdbcTemplate.update(
				sqlService.getSql("post.deleteByBlogId"),
				blog.getId());
	}

	
	/*
	public void update(User user) {
		jdbcTemplate.update(
				"update users set name = ?, password = ?, user_level = ?, login = ?, recommended = ? " +
				"where id = ?",
				user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(),
				user.getId());
	}
	*/
	
}
