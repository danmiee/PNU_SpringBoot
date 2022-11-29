package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {

	// H2 DB와 연결하며 DB와 연결을 담당하는 Dao에서 구현됨
	private MemberDao dao;

	public MemberService() {
		this.dao = new MemberDao();
	}

	public List<MemberVO> getMembers() {
		return dao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return dao.getMember(id);
	}

	public MemberVO addMember(MemberVO vo) {
		return dao.addMember(vo);
	}

	public MemberVO updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	public boolean removeMember(Integer id) {
		return dao.removeMember(id);
	}


}
