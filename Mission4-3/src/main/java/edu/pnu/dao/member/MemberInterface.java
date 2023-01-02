package edu.pnu.dao.member;

import java.util.Map;

import edu.pnu.domain.MemberVO;

// 로그 기록을 위해 리턴형태 모두 Map으로 변경
public interface MemberInterface {

	Map<String, Object> getMembers();

	Map<String, Object> getMember(Integer id);

	Map<String, Object> addMember(MemberVO vo);

	Map<String, Object> updateMember(MemberVO vo);

	Map<String, Object> removeMember(Integer id);

}