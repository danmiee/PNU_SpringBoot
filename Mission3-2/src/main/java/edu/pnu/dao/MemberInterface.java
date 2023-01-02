package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	List<MemberVO> getMembers();

	MemberVO getMember(Integer id);

	MemberVO addMember(MemberVO vo);

	MemberVO updateMember(MemberVO vo);

	MemberVO delMember(Integer id);

}