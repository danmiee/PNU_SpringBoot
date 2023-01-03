package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;

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
		
		
	}

}
