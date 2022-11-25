package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberDAO {
	public List<MemberVO> getMembers();
	public MemberVO getMember(String uid);
	public MemberVO addMember(MemberVO vo);
	public MemberVO updateMember(MemberVO vo);
	public MemberVO removeMember(String id);
}
