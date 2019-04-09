package com.taiji.Abner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJdbc1ApplicationTests {

	@Autowired
	JdbcTemplate jdbc;

	// 具名参数
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int countOfActorsByFirstName(String firstName) {

		String sql = "select count(*) from T_ACTOR where first_name = :first_name";
		SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", firstName);

		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
	}

	@Test
	public void namedtemplate() {
		System.out.println(countOfActorsByFirstName("哈哈哇"));
	}

	// 简单插入
	@Autowired
	DataSource data;

	@Test
	public void simpleInsert() {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(data).withTableName("t_actor");
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("id", "4");
		parameters.put("first_name", "六六六");
		parameters.put("last_name", "贼溜");
		int flag = simpleJdbcInsert.execute(parameters);
		System.out.println(flag);
	}

	@Test
	public void contextLoads() {

		int rowCount = this.jdbc.queryForObject("select count(*) from emp", Integer.class);
		System.out.println(rowCount);

		List rowName = this.jdbc.queryForList("select * from emp where ename = '小王'");
		System.out.println(rowName);

		int rowUpdate = this.jdbc.update("update emp set ename = '普普' where ename= 'laoyu'");

		RowMapper<Actor> rowMapper = new RowMapper<Actor>() {
			public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Actor actor = new Actor();
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setId(rs.getInt("id"));
				return actor;
			}
		};

		// 更新插入数据
		// System.out.println("请输入sid：");
		// Scanner sc = new Scanner(System.in);
		// String sid = sc.nextLine();
		//
		// System.out.println("请输入姓名：");
		// Scanner sc1 = new Scanner(System.in);
		// String name = sc1.nextLine();
		//
		// int flag = this.jdbc.update("insert into stu(sid,name) values (?,?)", sid,
		// name);
		// System.out.println("第" + flag + "行受到了影响");
		// List rowHf = this.jdbc.queryForList("select * from stu");
		// System.out.println(rowHf);

		// 单一
		// Actor actor = this.jdbc.queryForObject("select * from t_actor where id = ?",
		// new Object[] { 1 }, rowMapper);
		// System.out.println(actor.toString());

		// 多查询
		// List<Actor> actors = this.jdbc.query("select id,first_name,last_name from
		// t_actor ", rowMapper);
		// System.out.println(actors);
	}

}
