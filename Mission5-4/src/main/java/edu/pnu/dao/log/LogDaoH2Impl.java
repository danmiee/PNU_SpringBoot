package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@Repository
public class LogDaoH2Impl implements LogInterface {

	private Connection con = null;

	public LogDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission4", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addLog(String method, String sql, boolean success) {
		
		
		String query = "insert into dblog(method,sqlstring,success) values (?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, method);
			ps.setString(2, sql);
			ps.setBoolean(3, success);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (ps!=null)	ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
