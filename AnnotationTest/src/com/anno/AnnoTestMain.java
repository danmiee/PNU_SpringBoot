package com.anno;

import java.lang.reflect.Method;

public class AnnoTestMain {
	
	@MyAnnotation(count=5)
	public static void print() {
		System.out.println("print");
	}
	
	public static void main(String[] args) throws Exception {
		// 클래스 정보 가져오기
//		AnnoTestMain at = new AnnoTestMain();
//		at.getClass();
		
		// 메소드에 대한 정보를 m에 담음
		Method m = AnnoTestMain.class.getMethod("print");
		
		if(m.isAnnotationPresent(MyAnnotation.class)) {
			MyAnnotation method = m.getAnnotation(MyAnnotation.class);
			for(int i=0; i<method.count(); i++) {
				print();
			}
		} else {
			print();
		}
	}
}
