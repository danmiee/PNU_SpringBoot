package edu.pnu.dao.member;

import java.util.Map;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	Map<String, Object> getMembers();

	Map<String, Object> getMember(int id);

	Map<String, Object> addMember(MemberVO vo);

	Map<String, Object> updateMember(MemberVO vo);

	Map<String, Object> removeMember(int id);

}