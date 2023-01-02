package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogInterface {

	// DB 연결
	private Connection con = null;

	public LogDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission2", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	// insert into dblog (method, sql, success) values (?, ?, ?)
	public void addLog(String method, String sql, boolean success) {
		// sql 매개변수 변함 > ps
		PreparedStatement ps = null;
		try {
			// 전송할 쿼리문 준비
			ps = con.prepareStatement("insert into dblog (method,sqlstring,success) values (?,?,?)");
			ps.setString(1, method);
			ps.setString(2, sql);
			ps.setBoolean(3, success);
			// 쿼리문 전송(select 아니므로 update)
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
