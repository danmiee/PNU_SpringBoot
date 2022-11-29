package edu.pnu.service;

import java.util.List;
import org.springframework.stereotype.Service;
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
