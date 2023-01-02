package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	// interface 객체 선언
	private MemberInterface memberDao;
	
	public MemberService() {
		// dao 선택지 부여 
		this.memberDao = new MemberDaoH2Impl();
//		this.memberDao = new MemberDaoListImpl();
	}

	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}

	public MemberVO addMember(MemberVO vo) {
		return memberDao.addMember(vo);
	}

	public MemberVO updateMember(MemberVO vo) {
		return memberDao.updateMember(vo);
	}

	public MemberVO delMember(Integer id) {
		return memberDao.delMember(id);
	}

}
