package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	@Autowired
	MemberInterface memberDao;
	@Autowired
	LogInterface logDao;
	
	Map<String, Object> map;
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		map = memberDao.getMembers();
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		if (data != null)	logDao.addlog("get", sql, true);
		else				logDao.addlog("get", sql, false);
		return (List<MemberVO>) data;
	}
	
	public MemberVO getMember(int id) {
		map = memberDao.getMember(id);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		if (data != null)	logDao.addlog("get", sql, true);
		else				logDao.addlog("get", sql, false);
		return (MemberVO) data;
	}
	
	public MemberVO addMember(MemberVO vo) {
		map = memberDao.addMember(vo);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		if (data != null)	logDao.addlog("post", sql, true);
		else				logDao.addlog("post", sql, false);
		return (MemberVO) data;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		map = memberDao.updateMember(vo);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		if (data != null)	logDao.addlog("put", sql, true);
		else				logDao.addlog("put", sql, false);
		return (MemberVO) data;
	}
	
	public MemberVO removeMember(int id) {
		map = memberDao.removeMember(id);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		if (data != null)	logDao.addlog("delete", sql, true);
		else				logDao.addlog("delete", sql, false);
		return (MemberVO) data;
	}
	
}
