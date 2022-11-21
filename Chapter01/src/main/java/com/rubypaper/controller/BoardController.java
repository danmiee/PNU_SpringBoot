package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {

	public BoardController() {
		System.out.println("===> BoardController 생성");
	}

	@PostMapping("/hello")
	public String hello1(String name) {
		return "Post Hello : " + name;
	}
	
	//  
	@GetMapping("/hello")
	public String hello2(String name) {
		return "Get Hello : " + name;
	}
	
	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello : " + name;
	}
	
	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
			board.setSeq(1);
			board.setTitle("테스트 제목...");
			board.setWriter("테스터");
			board.setContent("테스트 내용입니다..........");
			board.setCreateDate(new Date());
			board.setCnt(0);
			return board;
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("제목" + i);
			board.setWriter("테스터");
			board.setContent(i + "번 내용입니다.");
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}

}
