package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	// 쿼리 메소드
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByTitleContaining(String string);
	List<Board> findByTitleContainingAndCntGreaterThan(String string, long l);
	List<Board> findByCntBetweenOrderBySeqAsc(int i, int j);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String string, String string2);
	
	
	// 쿼리 어노테이션
	
	// 위치 기반 파라미터 %%?1%
//	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
//	List<Board> queryAnnotationTest1(String searchKeyword);

	// 이름 기반 파라미터 %:searchKeyword
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest1(@Param("searchKeyword") String keyword);
//	List<Board> queryAnnotationTest1(String searchKeyword);		// 매개변수명과 파라미터 변수명이 같으면 @Param 생략 가능
	
	// 특정 변수만 조회
	@Query("select b.seq, b.title, b.writer, b.createDate from Board b "
			+ "where b.title like %?1% order by b.seq desc")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String stringKeyword);
	
	// 네이티브 쿼리
	// test1,2에서는 default인 value값만 있어 생략하였으나 여기서는 nativeQuery값도 있으므로 value= 기재
	@Query(value="select seq, title, writer, createdate from board"
			+ "where title like '%'||?1||'%' order by seq desc", nativeQuery=true)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
			
	// 페이징 처리
	@Query("select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest4(Pageable paging);
}
