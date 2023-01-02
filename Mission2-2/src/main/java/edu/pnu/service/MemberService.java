package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

// service : controller-dao 연결 역할
@Service
public class MemberService {

	// dao 변수 선언
	private MemberDao memberDao;
	
	// 임시데이터 넣어주기
	public MemberService() {
		this.memberDao = new MemberDao();
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
