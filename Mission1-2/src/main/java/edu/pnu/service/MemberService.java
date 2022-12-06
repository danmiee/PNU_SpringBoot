package edu.pnu.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberService {

	// 멤버 저장할 리스트 전역변수로 선언
	List<MemberVO> list;
	
	public List<MemberVO> getMembers(){
		return list;
	}
	
	public MemberVO getMember(@PathVariable Integer id) {
		for(MemberVO m : list) {
			
		}
		return null;
	}

	public MemberVO postMember(MemberVO vo) {
		return vo;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		return vo;
	}
	
	public MemberVO delMember(@PathVariable Integer id) {
		return null;
	}

}
