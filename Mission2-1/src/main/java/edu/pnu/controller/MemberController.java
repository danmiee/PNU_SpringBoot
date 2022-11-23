package edu.pnu.controller;

import java.util.List;

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

	// 서비스클래스를 멤버로 가짐
	private MemberService memberService;
	
	// 생성자
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable String id) {
		return memberService.getMember(id);
	}

	@PostMapping("/member")
	public MemberVO addMember(MemberVO vo) {
		return memberService.addMember(vo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		return memberService.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable String id) {
		return memberService.removeMember(id);
	}
	
}
