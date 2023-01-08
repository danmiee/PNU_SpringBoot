package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	private MemberInterface memberDao;
	// LogInterface 객체 2개 생성
	private LogInterface logDao;
	private LogInterface logDao2;
	
	// 필드 사용 생성자
	public MemberService(MemberInterface memberDao,
			// LogInterface 객체 Qualifier로 구분
			@Qualifier("LogDaoH2Impl") LogInterface logDao, 
			@Qualifier("LogDaoFileImpl") LogInterface logDao2) {
		this.memberDao = memberDao;
		this.logDao = logDao;
		this.logDao2 = logDao2;
	}

	// LogInterface 통합 메소드
	public void addlog(Map<String, Object> map, String method, boolean success) {
		String sql = (String) map.get("sql");
		logDao.addlog(method, sql, success);
		logDao2.addlog(method, sql, success);
	}
	
	// 이하 메소드 모두 통합 addlog 활용하도록 변경
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> data = (List<MemberVO>) map.get("data");
		if (data != null)	addlog(map, "get", true);
		else				addlog(map, "get", false);
		return data;
	}
	
	public MemberVO getMember(int id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO data = (MemberVO) map.get("data");
		if (data != null)	addlog(map, "get", true);
		else				addlog(map, "get", false);
		return data;
	}
	
	public MemberVO addMember(MemberVO vo) {
		Map<String, Object> map = memberDao.addMember(vo);
		MemberVO data = (MemberVO) map.get("data");
		if (data != null)	addlog(map, "post", true);
		else				addlog(map, "post", false);
		return data;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		Map<String, Object> map = memberDao.updateMember(vo);
		MemberVO data = (MemberVO) map.get("data");
		if (data != null)	addlog(map, "put", true);
		else				addlog(map, "put", false);
		return data;
	}
	
	public MemberVO removeMember(int id) {
		Map<String, Object> map = memberDao.removeMember(id);
		MemberVO data = (MemberVO) map.get("data");
		if (data != null)	addlog(map, "delete", true);
		else				addlog(map, "delete", false);
		return data;
	}
	
}
