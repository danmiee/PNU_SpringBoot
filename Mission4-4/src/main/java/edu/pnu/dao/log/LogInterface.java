package edu.pnu.dao.log;

public interface LogInterface {

	public void addLog(String method, String sql, boolean success);
}
