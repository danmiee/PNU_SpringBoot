package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogDaoFileImpl implements LogInterface {

	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		try {
			File file = new File("mission1.txt");
			FileWriter fw = new FileWriter(file, true); // append 모드
//			FileWriter fw = new FileWriter(file); // 덮어쓰기(append-기본값:false)
			fw.write(method + " / " + sqlstring + " / " + success + "\n");
			fw.close();
			System.out.println("logged: " + method + " / " + sqlstring + " / " + success);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
