package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
//	MemberInterface dao = new MemberDaoH2Impl();
	MemberInterface dao = new MemberDaoListImpl();
	
	public List<MemberVO> getMembers(){
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
	
	public MemberVO removeMember(Integer id) {
		return dao.removeMember(id);
	}
}
