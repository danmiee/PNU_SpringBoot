package edu.pnu.service;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@PostConstruct
	public void initData() {
		
		// 조회수 랜덤 배정
		Random random = new Random();
		
		// 데이터 생성
		for (int i=1; i<=5; i++) {
			boardRepo.save(new Board("제목"+i, "홍길동", "내용"+i, random.nextLong(100)));			
		}
		for (int i=6; i<=10; i++) {
			boardRepo.save(new Board("제목"+i, "아무개", "내용"+i, random.nextLong(100)));			
		}
	}
	
	public List<Board> getBoardList(Board board) {
		return (List<Board>)boardRepo.findAll(); 
	}
	
	public void insertBoard(Board board) {
		// 매개변수로 받은 엔티티를 save로 영속화
		boardRepo.save(board);
	}
	
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}
	
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}
	
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
}
