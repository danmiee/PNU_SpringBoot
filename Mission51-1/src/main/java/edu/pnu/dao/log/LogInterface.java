package edu.pnu.dao.log;

public interface LogInterface {
	
	// 입력할 값을 매개변수로 갖는 메소드
	void addLog(String method, String sqlstring, boolean success);
	
}
