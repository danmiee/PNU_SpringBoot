package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@Repository
public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addlog(String method, String sqlstring, boolean success) {

		try {
			File f = new File("log.csv");
			FileWriter fw = new FileWriter(f, true);
			fw.write(method + "," + sqlstring + "," + success + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
