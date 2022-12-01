package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class QueryAnnotationTest {

	@Autowired
	private BoardRepository boardRepo;

//	@Test
	void testQueryAnnotationTest1() {
		// title이 "title10"인 데이터 찾기 - 위치기반 or 이름기반 (Repo에서 선택)
		List<Board> boardList = boardRepo.queryAnnotationTest1("title10");

		System.out.println("result");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

//	@Test
	void testQueryAnnotationTest2() {
		// 특정 변수만 조회
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title10");

		System.out.println("result");
		for (Object[] row : boardList) {
			System.out.println("---> " + Arrays.toString(row));
		}
	}

//	@Test
	void testQueryAnnotationTest3() {
		// 네이티브 쿼리
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title10");

		System.out.println("result");
		for (Object[] row : boardList) {
			System.out.println("---> " + Arrays.toString(row));
		}
	}

	@Test
	void testQueryAnnotationTest4() {
		// 페이징처리
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);

		System.out.println("result");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
}
