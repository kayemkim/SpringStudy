package nephilim.study.spring.ch06.practice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import nephilim.study.spring.ch06.practice.model.Blog;
import nephilim.study.spring.ch06.practice.model.Post;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PostDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Post> postRowMapper = new RowMapper<Post>(){

		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			Post post = new Post();
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setCreated(rs.getTimestamp("created"));
			return post;
		}
	};
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Post get(Blog blog, int id) {
		return jdbcTemplate.queryForObject("select * from post where id = ?",
				new Integer[] {id},
				postRowMapper);
	}
	
	public List<Post> get(Blog blog) {
		List<Post> blogs = null;
		
		blogs = jdbcTemplate.query("select * from post where blog_id = ? order by id", 
					new Integer[]{ blog.getId() }, 
					postRowMapper);
		
		return blogs;
	}
	
	public void add(Blog blog, Post post) {
		Timestamp created = new Timestamp(post.getCreated().getTime());
		jdbcTemplate.update(
				"insert into post(id, blog_id, title, content, created) " +
				"values(seq_post_id.nextval, ?,?,?,?)",
				blog.getId(),
				post.getTitle(),
				post.getContent(),
				created
				);
	}
	
	public void deleteAllPost(Blog blog) {
		jdbcTemplate.update("delete from post where blog_id =?", blog.getId());
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
