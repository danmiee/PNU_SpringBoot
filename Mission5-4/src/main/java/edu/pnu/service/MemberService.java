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
	private MemberInterface memberDao;
	@Autowired
	private LogInterface logDao;
	
	public MemberService() {
		System.out.println("Call MemberService");
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers(){
		Map<String, Object> map = memberDao.getMembers();
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		
		if(data!=null)	logDao.addLog("get", sql, true);
		else			logDao.addLog("get", sql, false);
		
		return (List<MemberVO>) data;
	}
	
	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		
		if(data!=null)	logDao.addLog("get", sql, true);
		else			logDao.addLog("get", sql, false);
		
		return (MemberVO) data;
		}
	
	public MemberVO addMember(MemberVO vo) {
		Map<String, Object> map = memberDao.addMember(vo);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		
		if(data!=null)	logDao.addLog("get", sql, true);
		else			logDao.addLog("get", sql, false);
		
		return (MemberVO) data;
		}
	
	public MemberVO updateMember(MemberVO vo) {
		Map<String, Object> map = memberDao.updateMember(vo);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		
		if(data!=null)	logDao.addLog("get", sql, true);
		else			logDao.addLog("get", sql, false);
		
		return (MemberVO) data;
		}
	
	public MemberVO removeMember(Integer id) {
		Map<String, Object> map = memberDao.removeMember(id);
		String sql = (String) map.get("sql");
		Object data = map.get("data");
		
		if(data!=null)	logDao.addLog("get", sql, true);
		else			logDao.addLog("get", sql, false);
		
		return (MemberVO) data;
		}
}
