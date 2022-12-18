package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository
public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sqlString, boolean success) {
		File file = new File("dbLog.csv");
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + "," + sqlString + "," + success+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
