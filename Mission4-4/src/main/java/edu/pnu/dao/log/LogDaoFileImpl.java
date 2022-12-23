package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sql, boolean success) {
		File f = new File("log.csv");
		try {
			FileWriter fw = new FileWriter(f,true);
			fw.write(method + "," + sql + "," + success + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
