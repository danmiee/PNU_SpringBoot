package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberInterface dao;
	
	@Autowired
	private LogInterface log;

	public List<MemberVO> getMembers() {
		List<MemberVO> list = dao.getMembers();
		if(list!=null) {
			log.addLog("get", dao.getSqlString(), true);
		} else {
			log.addLog("get", dao.getSqlString(), false);
		}
		return list;
	}

	public MemberVO getMember(Integer id) {
		MemberVO m = dao.getMember(id);
		if(m!=null) {
			log.addLog("get", dao.getSqlString(), true);
		} else {
			log.addLog("get", dao.getSqlString(), false);
		}
		return m;
	}

	public MemberVO addMember(MemberVO vo) {
		MemberVO m = dao.addMember(vo);
		if(m!=null) {
			log.addLog("post", dao.getSqlString(), true);
		} else {
			log.addLog("post", dao.getSqlString(), false);
		}
		return m;
	}

	public MemberVO updateMember(MemberVO vo) {
		MemberVO m = dao.updateMember(vo);
		if(m!=null) {
			log.addLog("put", dao.getSqlString(), true);
		} else {
			log.addLog("put", dao.getSqlString(), false);
		}
		return m;
	}

	public MemberVO removeMember(Integer id) {
		MemberVO m = dao.removeMember(id);
		if(m!=null) {
			log.addLog("delete", dao.getSqlString(), true);
		} else {
			log.addLog("delete", dao.getSqlString(), false);
		}
		return m;
	}
}
