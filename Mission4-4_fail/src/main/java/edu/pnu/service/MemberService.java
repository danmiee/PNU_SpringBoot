package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDaoH2Impl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoListImpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
//	MemberInterface memberDao = new MemberDaoH2Impl();
	MemberInterface memberDao = new MemberDaoListImpl();
	
	LogInterface logDao = new LogDaoH2Impl();
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers(){
		
		String sql = (String) memberDao.getMembers().get("sql");
		List<MemberVO> data = (List<MemberVO>) memberDao.getMembers().get("data");
		
		if(data!=null) {
			logDao.addLog("get", sql, true);
		} else {
			logDao.addLog("get", sql, false);
		}
		return data;
	}
	
	public MemberVO getMember(Integer id) {
		
		String sql = (String) memberDao.getMember(id).get("sql");
		MemberVO data = (MemberVO) memberDao.getMember(id).get("data");
		
		if(data!=null) {
			logDao.addLog("get", sql, true);
		} else {
			logDao.addLog("get", sql, false);
		}
		return data;
	}
	
	public MemberVO addMember(MemberVO vo) {
		
		String sql = (String) memberDao.addMember(vo).get("sql");
		MemberVO data = (MemberVO) memberDao.addMember(vo).get("data");
		
		if(data!=null) {
			logDao.addLog("post", sql, true);
		} else {
			logDao.addLog("post", sql, false);
		}
		return data;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		
		String sql = (String) memberDao.updateMember(vo).get("sql");
		MemberVO data = (MemberVO) memberDao.updateMember(vo).get("data");
		
		if(data!=null) {
			logDao.addLog("put", sql, true);
		} else {
			logDao.addLog("put", sql, false);
		}
		return data;
	}
	
	public MemberVO removeMember(Integer id) {
		
		String sql = (String) memberDao.removeMember(id).get("sql");
		MemberVO data = (MemberVO) memberDao.removeMember(id).get("data");
		
		if(data!=null) {
			logDao.addLog("delete", sql, true);
		} else {
			logDao.addLog("delete", sql, false);
		}
		return data;
	}
}
