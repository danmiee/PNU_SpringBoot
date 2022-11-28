package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import edu.pnu.dao.MemberInterface;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.domain.MemberVO;

@Service
@SuppressWarnings("unused")
public class MemberService {

	private MemberInterface dao;

	public MemberService() {
		dao = new MemberDaoH2Impl();
//		dao = new MemberDaoListImpl();
	}

	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return dao.getMembers();
	}

	@GetMapping("/member/{id}")
	public MemberVO getMember(Integer id) {
		return dao.getMember(id);
	}

	@PostMapping("/member")
	public MemberVO addMember(MemberVO vo) {
		return dao.addMember(vo);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	@DeleteMapping("/member")
	public boolean removeMember(Integer id) {
		return dao.removeMember(id);
	}
}
