package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDaoFileImpl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoListImpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@SuppressWarnings("unused")
@Service
public class MemberService {

	/*
	 * logDao 구현 idea 1. Map 객체(key:메소드명, value:결과값) 생성 2. 1의 객체에서 data 얻기 3. data가
	 * null이 아닐 때 성공여부에 따라 로그 기록하기
	 */

	private MemberInterface memberDao;
	private LogInterface logDao;

	public MemberService() {
//		memberDao = new MemberDaoH2Impl();
		memberDao = new MemberDaoListImpl();
//		logDao = new LogDaoH2Impl();
		logDao = new LogDaoFileImpl();
	}

	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		// sql(query문)은 String, data는 MemberVO 형태로 저장되므로 Value는 Object
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		if (list != null)
			logDao.addLog("get", (String) map.get("sql"), true);
		else
			logDao.addLog("get", (String) map.get("sql"), false);
		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("get", (String) map.get("sql"), true);
		else
			logDao.addLog("get", (String) map.get("sql"), false);
		return m;
	}

	public MemberVO addMember(MemberVO vo) {
		Map<String, Object> map = memberDao.addMember(vo);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("post", (String) map.get("sql"), true);
		else
			logDao.addLog("post", (String) map.get("sql"), false);
		return m;
	}

	public MemberVO updateMember(MemberVO vo) {
		Map<String, Object> map = memberDao.updateMember(vo);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("put", (String) map.get("sql"), true);
		else
			logDao.addLog("put", (String) map.get("sql"), false);
		return m;
	}

	public MemberVO delMember(Integer id) {
		Map<String, Object> map = memberDao.delMember(id);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("delete", (String) map.get("sql"), true);
		else
			logDao.addLog("delete", (String) map.get("sql"), false);
		return m;
	}
}
