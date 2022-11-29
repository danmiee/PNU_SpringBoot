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

// 컨트롤러 어노테이션 넣어야 연동됨
@RestController
public class MemberController {
	// controller는 웹브라우저를 통한 요청을 받아들이기만 함
	// 서비스 제공은 service에서 할 것이므로 모든 메소드에 대하여 service 리턴
	
	private MemberService ms;

	// 기본생성자 컨트롤러 실행 시 서비스 호출
	@Autowired
	public MemberController() {
		ms = new MemberService();
		System.out.println("MemberController() 생성자가 호출됨");
	}

	// 요청방식 및 주소 매핑 어노테이션 기재
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		// 호출명령 확인을 위한 콘솔 노출
		System.out.println("MemberController - getMembers()가 호출됨");
		// 서비스 객체를 활용하여 서비스 리턴
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.println("MemberController - getMember()가 호출됨");
		return ms.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO vo) {
		System.out.println("MemberController - addMember()가 호출됨");
		return ms.addMember(vo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		System.out.println("MemberController - updateMember()가 호출됨");
		return ms.updateMember(vo);
		
	}
	
	@DeleteMapping("/member/{id}")
	public boolean removeMember(@PathVariable Integer id) {
		System.out.println("MemberController - removeMember()가 호출됨");
		return ms.removeMember(id);
		
	}
	
}
