package nephilim.study.spring.ch07.practice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import nephilim.study.spring.ch07.practice.model.Blog;
import nephilim.study.spring.ch07.practice.sqlservice.SqlService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BlogDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private SqlService sqlService;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Blog> blogRowMapper = new RowMapper<Blog>() {
		
		public Blog mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Blog blog= new Blog();
			blog.setId(rs.getInt("id"));
			blog.setName(rs.getString("name"));
			blog.setAddress(rs.getURL("address"));
			blog.setEmail(rs.getString("email"));
			return blog;
		}
	};

	public int createId() {
		return jdbcTemplate.queryForInt( sqlService.getSql("blog.createId"));
	}
	
	public Blog get(int id) {
		return jdbcTemplate.queryForObject(
				sqlService.getSql("blog.getById"),
				new Integer[] {id},
				blogRowMapper);
	}
	
	public List<Blog> getAll() {
		
		List<Blog> blogs = jdbcTemplate.query(
				sqlService.getSql("blog.getAll"),
				blogRowMapper);
		return blogs;
	}
	
	public void add(Blog blog) {
		jdbcTemplate.update(
				sqlService.getSql("blog.insert"),
				blog.getId(),
				blog.getName(),
				blog.getEmail(),
				blog.getAddress().toString());
	}
	
	public void deleteAll() {
		jdbcTemplate.update(sqlService.getSql("blog.deleteAll"));
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
