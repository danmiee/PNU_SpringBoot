package edu.pnu;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;

//	@Test
	void dataPrepare() {
		Random random = new Random();
		for (int i = 1; i <= 100; i++) {
			boardRepo.save(new Board("title" + i, "writer" + i, "content" + i, random.nextLong(100)));
//			boardRepo.save(new Board(100L, "title" + i, "writer" + i, "content" + i, new Date(), random.nextLong(100)));
		}
		
	}

	@Test
	void find() {
		// title이 "title10"인 데이터 출력
//		List<Board> boardList = boardRepo.findByTitle("1");
		
		// title에 "1"이 포함되는 데이터 출력
//		List<Board> boardList = boardRepo.findByTitleContaining("1");
		
		// title에 "1"이 포함되면서 cnt가 50보다 큰 데이터 출력
//		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1",50L);
		
		// cnt가 10~50 사이인 데이터를 seq 오름차순으로 출력
//		List<Board> boardList = boardRepo.findByCntBetweenOrderBySeqAsc(10,50);
		
		// title에 "10"이 포함되거나 context에 "2"가 포함되는 데이터를 seq 내림차순으로 출력
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10","2");
		
		System.out.println("result");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}	
}
