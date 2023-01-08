package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository("LogDaoFileImpl")	// 객체명 기재
public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addlog(String method, String sqlstring, boolean success) {

		try {
			// 쿼리문 내 콤마로 인해 csv파일로 저장 시 추가 작업 필요
			File f = new File("log.txt");
			FileWriter fw = new FileWriter(f, true);
			fw.write(method + "," + sqlstring + "," + success + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
