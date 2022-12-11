package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
//	@Autowired
	private MemberService ms;
	
	// 객체의존성 갖는 부분 - 생성자로 annotation 주입
	@Autowired
	public MemberController(MemberService ms) {
		 this.ms = ms;	// new 연산자로 정의하면 NPE 발생
		 System.out.println("MemberController - 생성자 호출");
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers(){
		System.out.println("MemberController - getMembers()");
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.printf("MemberController - getMember(%d)\n",id);
		return ms.getMember(id);
	}

	@PostMapping("/member")
	public MemberVO postMember(MemberVO vo) {
		System.out.printf("MemberController - addMember(%s)\n",vo);
		return ms.addMember(vo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		System.out.printf("MemberController - updateMember(%s)\n",vo);
		return ms.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO delMember(@PathVariable Integer id) {
		System.out.printf("MemberController - delMember(%d)\n",id);
		return ms.delMember(id);
	}
	
}
