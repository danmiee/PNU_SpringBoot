package edu.pnu.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogInterface {

	private JdbcTemplate jdbcTemplate;
	
	// 필드 사용 생성자 선언
	@Autowired
	public LogDaoH2Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addlog(String method, String sqlstring, boolean success) {
		String sqlString = "insert into dblog(method, sqlstring, success) values (?, ?, ?)"; 
		try {
			// insert문이므로 jdbcTemplate.update 사용
			jdbcTemplate.update(sqlString, method, sqlstring, success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
