package edu.pnu.dao.log;

public interface LogInterface {
	
	public void addlog(String method, String sqlstring, boolean success);
	
}
