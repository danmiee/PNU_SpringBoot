package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDaoFileImpl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoListImpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	private MemberInterface memberDao;
	private LogInterface logDao;
	
	public MemberService() {
//		memberDao = new MemberDaoH2Impl();
		memberDao = new MemberDaoListImpl();
//		logDao = new LogDaoH2Impl();
		logDao = new LogDaoFileImpl();
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
