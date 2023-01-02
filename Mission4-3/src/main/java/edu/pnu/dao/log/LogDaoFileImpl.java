package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;

public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sqlString, boolean success) {
		try {
			// 파일열기 - 파일명 기재 필수
			File file = new File("dbLog.csv");
			// 파일쓰기 - true 미기재 시 파일 덮어쓰기 됨
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + "," + sqlString + "," + success+"\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
