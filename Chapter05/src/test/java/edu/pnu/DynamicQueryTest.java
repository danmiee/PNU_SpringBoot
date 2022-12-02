package edu.pnu;

import java.util.List;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Autowired
	private EntityManager em;
	
//	@Test
	public void testDynamicQuery() {
		String searchCondition = "title";
		String searchKeyword = "title10";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("title")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("content")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder, paging);
				
		System.out.println("Result: ");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	@Test
	public void testDynamicQuery2() {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QBoard b = QBoard.board;
		
		List<Board> list = queryFactory.selectFrom(b)
				.where(b.cnt.lt(50))
				.orderBy(b.seq.asc())
				.fetch();
		
		for(Board bb : list) {
			System.out.println("---> " + bb);
		}
	}
}
