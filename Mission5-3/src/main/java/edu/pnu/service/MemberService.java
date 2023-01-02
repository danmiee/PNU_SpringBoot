package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	// 의존성주입
	@Autowired
	private MemberInterface memberDao;
	
	@Autowired
	private LogInterface logDao;
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers(){
		Map<String, Object> map = memberDao.getMembers();
		String sql = (String) map.get("sql");
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		
		if (list != null)
			logDao.addLog("get", sql, true);
		else
			logDao.addLog("get", sql, false);
		
		return list;
	}
	
	public MemberVO getMember(@PathVariable Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("get", sql, true);
		else
			logDao.addLog("get", sql, false);
		return m;
	}
	
	public MemberVO addMember(MemberVO vo) {
		Map<String, Object> map = memberDao.addMember(vo);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("post", sql, true);
		else
			logDao.addLog("post", sql, false);
		return m;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		Map<String, Object> map = memberDao.updateMember(vo);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("put", sql, true);
		else
			logDao.addLog("put", sql, false);
		return m;
	}
	
	public MemberVO removeMember(@PathVariable Integer id) {
		Map<String, Object> map = memberDao.removeMember(id);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)
			logDao.addLog("delete", sql, true);
		else
			logDao.addLog("delete", sql, false);
		return m;
	}
}
