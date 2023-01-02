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

	MemberService ms;
	
	public MemberController() {
		ms = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		System.out.println("getMembers() success");
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable int id) {
		System.out.printf("getMember(%d) success\n",id);
		return ms.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO vo) {
		System.out.printf("addMember(%d) success\n",ms.getMembers().size()+1);
		return ms.addMember(vo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		System.out.printf("updateMember(%d) success\n",vo.getId());
		return ms.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable int id) {
		System.out.printf("removeMember(%d) success\n",id);
		return ms.removeMember(id);
	}
	
}
