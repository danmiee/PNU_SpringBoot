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
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission4", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addLog(String method, String sqlstring, boolean success) {
		// Statement로 하면 에러발생 > 사유 확인 필요
//		String query = String.format("insert into dblog(method,sqlstring,success) values ('%s','%s','%s')",
//				method, sqlstring, success);
//		
//		Statement st = null;

		String query = "insert into dblog(method,sqlstring,success) values (?,?,?)";
		PreparedStatement ps = null;
		
		try {
//			st = con.createStatement();
//			st.executeUpdate(query);
			ps = con.prepareStatement(query);
			ps.setString(1, method);
			ps.setString(2, sqlstring);
			ps.setBoolean(3, success);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				st.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
