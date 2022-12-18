package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sqlString, Boolean success) {
		// 파일열기 - 파일명 기재 필수
		File file = new File("dbLog.csv");
		try {
			// 파일쓰기
			FileWriter fw = new FileWriter(file);
			fw.write(method + "," + sqlString + "," + success);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
