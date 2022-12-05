package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
// 메소드의 리턴값이 뷰 이름
// RestController : Rest API를 위한 컨트롤러
public class BoardController {
	
	@GetMapping("/test1")
	public String test1() {
		// src/main/webapp 경로에서 찾음
		return "test1";
	}
	
//	@GetMapping("/test2")
//	public String test2() {
//		// WEB-INF에 넣는 이유 : 외부에서 접근하지 못하도록 하기 위함(보안목적)
//		return "test2"; // ==> /WEB-INF/board/test2.jsp
//	}
	
	@GetMapping("/test2")
	public void test2() {
	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		
		List<Board> boardList = new ArrayList<>();
		
		for(long i=1; i<=10; i++) {
			Board board = new Board();
			board.setSeq(i);
			board.setTitle("게시판 프로그램 테스트");
			board.setWriter("도우너");
			board.setContent("게시판 프로그램 테스트입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardList.add(board);
		}
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
	@RequestMapping("/getBoardList1")
	public ModelAndView getBoardList1() {
		
		ModelAndView mv = new ModelAndView();
		List<Board> boardList = new ArrayList<>();
		
		for(long i=1; i<=10; i++) {
			Board board = new Board();
			board.setSeq(i);
			board.setTitle("게시판 프로그램 테스트");
			board.setWriter("둘리");
			board.setContent("게시판 프로그램 테스트입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardList.add(board);
		}
		mv.addObject("boardList", boardList);
		mv.setViewName("getBoardList");
		return mv;
	}
	
	// JPA 연동하기
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList2")
	public String getBoardList3(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList", boardList);
		// src/lmain/webapp/WEB-INF/board/getBoardList.jsp 호출
		return "getBoardList";
	}
	
	// 게시글 등록 창
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		// src/lmain/webapp/WEB-INF/board/insertBoard.jsp 호출
		return "insertBoard";
	}
	
	// 게시물 등록 완료
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		// forward 아닌 redirect로 처리
		// localhost:8080/getBoardList2 호출
		return "redirect:getBoardList2";
	}
	
	// 게시물 상세 조회
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		// src/main/webapp/WEB-INF/board/getBoard.jsp 호출
		return "getBoard";
	}
	
	// 게시물 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList2";
	}
	
	// 게시물 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList2";
	}
}
