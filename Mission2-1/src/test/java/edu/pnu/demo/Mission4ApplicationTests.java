package edu.pnu.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.MemberDAO;
import edu.pnu.domain.MemberDaoH2Impl;
import edu.pnu.domain.MemberVO;

@SpringBootTest
class Mission4ApplicationTests {

	@Test
	void dbTest() {
		MemberDAO dao = new MemberDaoH2Impl();
		MemberVO vo = dao.getMember("jmin");
		System.out.println(vo);
	}
	
	@Test
	void test01() {
		System.out.println("test01");
	}

	@Test
	void test02() {
		System.out.println("test02");
	}
	
	@Test
	void test03() {
		System.out.println("test03");
	}
}
