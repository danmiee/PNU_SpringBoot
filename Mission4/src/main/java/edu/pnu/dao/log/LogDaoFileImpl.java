package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogDaoFileImpl implements LogDAO {

	@Override
	public void addLog(String method, String query, Long exedate, boolean success) {
		try {
			File file = new File("log.txt");
			FileWriter fw;
			fw = new FileWriter(file, true);
			fw.write(method + ", " + query + ", " + new Date(exedate) + ", " + success + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
