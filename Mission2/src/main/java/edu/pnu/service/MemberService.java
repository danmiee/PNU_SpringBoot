package edu.pnu.service;

import edu.pnu.domain.MemberDAO;
import edu.pnu.domain.MemberVO;

public class MemberService {

	MemberDAO dao = new MemberDAO();

	// 모든 멤버 정보를 JSON 형태로 브라우저에 출력
	public MemberVO getMembers() {
		MemberVO vo = dao.getMembersVO();
		return vo;
	}

	// 아이디가 {id} 인 member를 찾아서 브라우저에 출력
	public MemberVO getMember(String id) {
		MemberVO vo = dao.getMemberVO(id);
		return vo;
	}
	
	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO memberVO) {
		
		return null;
	}

	// 수정 대상 객체 정보를 전달, 수정된 객체를 출력
	public MemberVO updateMembers(MemberVO memberVO) {
		
		return null;
	}

	// 아이디가 {id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	public MemberVO removeMembers(Integer id) {
		
		return null;
	}
}
