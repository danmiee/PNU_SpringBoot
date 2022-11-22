package edu.pnu.domain;

import java.util.List;

public interface MemberDAO {
	public List<MemberVO> getMembers();
	public MemberVO getMember(String uid);
	public MemberVO addMember(MemberVO vo);
	public MemberVO updateMember(MemberVO vo);
	public MemberVO removeMember(String id);
}
