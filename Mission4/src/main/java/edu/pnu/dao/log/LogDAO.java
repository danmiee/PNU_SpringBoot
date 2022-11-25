package edu.pnu.dao.log;

public interface LogDAO {
	public void addLog(String method, String query, Long exedate, boolean success);
}
