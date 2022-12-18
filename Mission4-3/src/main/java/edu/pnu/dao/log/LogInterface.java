package edu.pnu.dao.log;

public interface LogInterface {
	
	void addLog(String method, String sqlString, boolean success);
	
}