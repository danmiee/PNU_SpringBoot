package edu.pnu.dao.member;

import java.util.Map;

import edu.pnu.domain.MemberVO;

// log 기록 위해 Map으로 리턴
public interface MemberInterface {

	Map<String, Object> getMembers();

	Map<String, Object> getMember(Integer id);

	Map<String, Object> addMember(MemberVO vo);

	Map<String, Object> updateMember(MemberVO vo);

	Map<String, Object> removeMember(Integer id);

}