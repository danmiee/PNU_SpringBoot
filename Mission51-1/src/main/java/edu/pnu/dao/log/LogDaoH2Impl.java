package edu.pnu.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogInterface {

	// template 선언
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LogDaoH2Impl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addLog(String method, String sqlString, boolean success) {
		String sql = "insert into dblog(method, sqlString, success) values (?, ?, ?)";
		try {
			jdbcTemplate.update(sql, method, sqlString, success);
			System.out.println("logged: " + method + " / " + sqlString + " / " + success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
