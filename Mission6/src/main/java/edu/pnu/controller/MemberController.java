package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	MemberService ms;
	
	public MemberController(MemberService ms) {
		System.out.printf("Called MemberController\n");
		this.ms = ms;
	}
	
	@Autowired
	public void setMemberService(MemberService ms) {
		this.ms = ms;
		System.out.println("MemberController called setMemberService()");
	}
	
	@GetMapping("/member")
	public List<Member> getMembers() {
		System.out.println("MemberController called getMembers()");
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable Long id) {
		System.out.printf("MemberController called getMember(%d)\n", id);
		return ms.getMember(id);
	}
	
	@PostMapping("/member")
	public Member addMember(Member vo) {
		System.out.printf("MemberController called addMember()\n");
		return ms.addMember(vo);
	}
	
	@PutMapping("/member")
	public Member updateMember(Member vo) {
		System.out.printf("MemberController called updateMember(%d)\n", vo.getId());
		return ms.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public Member removeMember(@PathVariable Long id) {
		System.out.printf("MemberController called removeMember(%d)\n", id);
		return ms.removeMember(id);
	}
	
}
