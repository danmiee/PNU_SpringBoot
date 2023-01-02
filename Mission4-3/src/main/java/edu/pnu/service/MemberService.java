package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.dao.log.LogDaoFileImpl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoListImpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

//	MemberInterface memberDao = new MemberDaoH2Impl();
	MemberInterface memberDao = new MemberDaoListImpl();
	
//	LogInterface logDao = new LogDaoH2Impl();
	LogInterface logDao = new LogDaoFileImpl();
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers(){
		// memberDao 결과값 가져오기
		Map<String, Object> map = memberDao.getMembers();
		String sql = (String) map.get("sql");
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		
		// 결과값 존재여부에 따라 success 판단하여 log 기록
		if (list != null)
			logDao.addLog("get", sql, true);
		else
			logDao.addLog("get", sql, false);
		
		// 조회결과 리턴 
		return list;
	}
	
	public MemberVO getMember(@PathVariable Integer id) {
		// memberDao 결과 가져오기
		Map<String, Object> map = memberDao.getMember(id);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		// 결과값 존재여부에 따라 success 판단하여 log 기록
		if (m != null)
			logDao.addLog("get", sql, true);
		else
			logDao.addLog("get", sql, false);
		return m;
	}
	
	public MemberVO addMember(MemberVO vo) {
		// memberDao 결과 가져오기
		Map<String, Object> map = memberDao.addMember(vo);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		// 결과값 존재여부에 따라 success 판단하여 log 기록
		if (m != null)
			logDao.addLog("post", sql, true);
		else
			logDao.addLog("post", sql, false);
		return m;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		// memberDao 결과 가져오기
		Map<String, Object> map = memberDao.updateMember(vo);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		// 결과값 존재여부에 따라 success 판단하여 log 기록
		if (m != null)
			logDao.addLog("put", sql, true);
		else
			logDao.addLog("put", sql, false);
		return m;
	}
	
	public MemberVO removeMember(@PathVariable Integer id) {
		// memberDao 결과 가져오기
		Map<String, Object> map = memberDao.removeMember(id);
		String sql = (String) map.get("sql");
		MemberVO m = (MemberVO) map.get("data");
		// 결과값 존재여부에 따라 success 판단하여 log 기록
		if (m != null)
			logDao.addLog("delete", sql, true);
		else
			logDao.addLog("delete", sql, false);
		return m;
	}
}
