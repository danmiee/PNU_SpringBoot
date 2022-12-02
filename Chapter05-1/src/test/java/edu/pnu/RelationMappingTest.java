package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class RelationMappingTest {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
//	@Test
	void ManyToOneInsert() {
		
		Member m1 = new Member();
		m1.setId("member1");
		m1.setPassword("member111");
		m1.setName("둘리");
		m1.setRole("User");
		memberRepo.save(m1);
		
		Member m2 = new Member();
		m2.setId("member2");
		m2.setPassword("member222");
		m2.setName("도우너");
		m2.setRole("Admin");
		memberRepo.save(m2);
		
		for (int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(m1);
			board.setTitle("둘리가 등록한 게시글 " + i);
			board.setContent("둘리가 등록한 게시글 내용 " +i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
		for (int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(m2);
			board.setTitle("도우너가 등록한 게시글 " + i);
			board.setContent("도우너가 등록한 게시글 내용 " +i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
	}

	@Test
	void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("[ " + board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		System.out.println("작성자 : " + board.getMember().getName());
		System.out.println("작성자 권한 : " + board.getMember().getRole());
	}
}
