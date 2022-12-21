package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	MemberDao dao = new MemberDao();
	
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
