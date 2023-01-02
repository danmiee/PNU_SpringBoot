package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDaoH2Impl implements LogInterface {
	
	private Connection con;

	public LogDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLog(String method, String sqlString, boolean success) {
		String query = "insert into dblog(method, sqlstring, success) values (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, method);
			ps.setString(2, sqlString);
			ps.setBoolean(3, success);
			ps.executeUpdate();
		} catch (SQLException e) {
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
