package edu.pnu.dao.log;

public interface LogInterface {

	// 로그 기록용 메소드
	void addLog(String method, String sql, boolean success);
	
}
