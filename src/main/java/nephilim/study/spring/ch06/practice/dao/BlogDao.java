package nephilim.study.spring.ch06.practice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import nephilim.study.spring.ch06.practice.model.Blog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BlogDao {
	
	private JdbcTemplate jdbcTemplate;
	
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
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int createId() {
		return jdbcTemplate.queryForInt(
				"select seq_blog_id.nextval from dual");
	}
	
	public Blog get(int id) {
		return jdbcTemplate.queryForObject(
				"select * from blog where id = ?",
				new Integer[] {id},
				blogRowMapper);
	}
	
	public List<Blog> getAll() {
		List<Blog> blogs = jdbcTemplate.query(
				"select * from blog order by id", blogRowMapper);
		return blogs;
	}
	
	public void add(Blog blog) {
		jdbcTemplate.update(
				"insert into blog(id, name, email, address) " +
				"values(?,?,?,?)",
				blog.getId(),
				blog.getName(),
				blog.getEmail(),
				blog.getAddress().toString());
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from blog");
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
