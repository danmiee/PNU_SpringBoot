package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;

// 양자택일(LogDaoH2Impl, LogDaoListImpl) 필요로 주석처리
//@Repository
public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sql, boolean success) {
		try {
			// 파일열기
			File file = new File("log.txt");
			// 파일쓰기
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + ", " + sql + ", " + success + "\n");
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
