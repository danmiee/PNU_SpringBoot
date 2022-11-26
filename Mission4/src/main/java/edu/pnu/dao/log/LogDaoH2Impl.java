package edu.pnu.dao.log;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//	seq : int / Auto Increase / Primary Key
//	method : varchar(10)
//	query : varchar(255)
//	exedate : Date
//	성공여부

import javax.servlet.ServletContext;

import edu.pnu.common.JDBConnect;

public class LogDaoH2Impl extends JDBConnect implements LogDAO {

	public LogDaoH2Impl() {
		super();
	}

	public LogDaoH2Impl(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public LogDaoH2Impl(ServletContext application) {
		super(application);
	}

	public void addLog(String method, String query, Long exedate, boolean success) {
		// TODO Auto-generated method stub
		try {
			// 로그에 기록할 쿼리문 템플릿
			String sql = "INSERT INTO dblog(method, query, exedate, success) VALUES (?, ?, ?, ?)";
				// exedate를 날짜형식으로 받아오면 형변환 어려우므로 long타입의 시스템 현재 시각으로 가져오기
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, method);
			ps.setString(2, query);
			// 쿼리문 템플릿에 세팅하면서 형변환(long to date)
			ps.setDate(3, new Date(exedate));
			ps.setBoolean(4, success);
			ps.executeUpdate();
			System.out.println("logged");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
