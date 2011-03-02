package nephilim.study.spring.ch05;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public User get(String id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?",
				new Object[] {id},
				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user= new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setLevel(Level.valueOf(rs.getInt("user_level")));
						user.setLogin(rs.getInt("login"));
						user.setRecommend(rs.getInt("recommended"));
						return user;
					}
		});
	}
	
	public List<User> getAll() {
		List<User> users = null;
		
		users = jdbcTemplate.query("select * from users order by id", new RowMapper<User>(){

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setLevel(Level.valueOf(rs.getInt("user_level")));
				user.setLogin(rs.getInt("login"));
				user.setRecommend(rs.getInt("recommended"));
				return user;
			}
			
		});
		
		return users;
	}
	
	public void add(User user) {
		jdbcTemplate.update(
				"insert into users(id, name, password, user_level, login, recommended) " +
				"values(?,?,?,?,?,?)",
				user.getId(),
				user.getName(),
				user.getPassword(),
				user.getLevel().intValue(),
				user.getLogin(),
				user.getRecommend());
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}

	public void update(User user) {
		jdbcTemplate.update(
				"update users set name = ?, password = ?, user_level = ?, login = ?, recommended = ? " +
				"where id = ?",
				user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(),
				user.getId());
	}

	/*
	private RowMapper<User> userMapper = new RowMapper<User> () {
		public User mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};
	
	/*
	public List<User> getAll() {
		// 
		return null;
	}
	*/
}
