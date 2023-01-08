package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addlog(String method, String sqlstring, boolean success) {

		try {
			// 파일열기
			File f = new File("log.csv");
			// 파일쓰기(append모드 true)
			FileWriter fw = new FileWriter(f, true);
			// 기록내용
			fw.write(method + "," + sqlstring + "," + success + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
