package edu.pnu.domain;

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

public class LogDAO extends JDBConnect {

	public LogDAO() {
		super();
	}

	public LogDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public LogDAO(ServletContext application) {
		super(application);
	}

	public void addLog(String string, String string2, Long date, boolean b) {
		// TODO Auto-generated method stub
		try {
			// 로그에 기록할 쿼리문 템플릿
			String query = "INSERT INTO log_table(method, query, exedate, success) VALUES (?, ?, ?, ?)";
				// exedate를 날짜형식으로 받아오면 형변환 어려우므로 long타입의 시스템 현재 시각으로 가져오기
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, string);
			ps.setString(2, string2);
			// 쿼리문 템플릿에 세팅하면서 형변환(long to date)
			ps.setDate(3, new Date(date));
			ps.setBoolean(4, b);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
