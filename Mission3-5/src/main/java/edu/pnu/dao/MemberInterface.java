package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	List<MemberVO> getMembers();

	MemberVO getMember(int id);

	MemberVO addMember(MemberVO vo);

	MemberVO updateMember(MemberVO vo);

	MemberVO removeMember(int id);

}