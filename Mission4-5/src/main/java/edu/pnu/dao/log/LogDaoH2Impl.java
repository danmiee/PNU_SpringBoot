package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDaoH2Impl implements LogInterface {

	private Connection con = null;
	
	public LogDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission5", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addlog(String method, String sqlstring, boolean success) {

		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into dblog(method, sqlstring, success) values (?, ?, ?)");
			ps.setString(1, method);
			ps.setString(2, sqlstring);
			ps.setBoolean(3, success);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (ps!=null)		ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
