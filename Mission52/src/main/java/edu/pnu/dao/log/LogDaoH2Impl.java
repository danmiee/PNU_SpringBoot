package edu.pnu.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("LogDaoH2Impl")	// 객체명 기재
public class LogDaoH2Impl implements LogInterface {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LogDaoH2Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addlog(String method, String sqlstring, boolean success) {
		String sqlString = "insert into dblog(method, sqlstring, success) values (?, ?, ?)"; 
		try {
			jdbcTemplate.update(sqlString, method, sqlstring, success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
