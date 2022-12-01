package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SuppressWarnings("unused")
@SpringBootTest
class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;

	@Test
	void contextLoads() {

		// Create Test
//		boardRepo.save(new Board(
//			10L, 
//			"title", 
//			"writer", 
//			"content", 
//			new Date(), 
//			0L)
//		);

//		for (int i = 0; i < 10; i++) {
//			boardRepo.save(new Board(
//				10L, 
//				"title" + i, 
//				"writer" + i, 
//				"content" + i, 
//				new Date(), 
//				(long) i));
//		};

		// Read Test
//		Board board = boardRepo.findById(1L).get();
//		System.out.println("=== 1번 게시글 조회 ===");
//		System.out.println(board.toString());

		// Update Test
//		System.out.println("=== 1번 게시글 제목 수정 ===");
//		board.setTitle("title 수정");
//		boardRepo.save(board);

		// Delete Test
		boardRepo.deleteById(1L);

	}

}
