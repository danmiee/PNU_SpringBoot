package edu.pnu.service;

import java.util.List;
import org.springframework.stereotype.Service;
import edu.pnu.dao.log.LogDaoH2Impl;
import edu.pnu.dao.log.LogDaoFileImpl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoH2Impl;
import edu.pnu.dao.member.MemberDaoListImpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@SuppressWarnings("unused")
public class MemberService {

	private MemberInterface dao;
	private LogInterface log;

	public MemberService() {
		dao = new MemberDaoH2Impl();
//		dao = new MemberDaoListImpl();
		
		log = new LogDaoH2Impl();
//		log = new LogDaoFileImpl();
	}

	public List<MemberVO> getMembers() {
		// 자기자신을 받는 객체 생성
		List<MemberVO> list = dao.getMembers();
		// 실행결과값 있을 때와 없을 때에 따라 success 내용 다르게 로그 남기기
		if(list!=null) {
			log.addLog("get", dao.getSql(), true);
		} else {
			log.addLog("get", dao.getSql(), false);
		}
		return list;
	}

	public MemberVO getMember(Integer id) {
		MemberVO m = dao.getMember(id);
		if(m!=null) {
			log.addLog("get", dao.getSql(), true);
		} else {
			log.addLog("get", dao.getSql(), false);
		}
		return m;
	}

	public MemberVO addMember(MemberVO vo) {
		MemberVO m = dao.addMember(vo);
		if(m!=null) {
			log.addLog("post", dao.getSql(), true);
		} else {
			log.addLog("post", dao.getSql(), false);
		}
		return m;
	}

	public MemberVO updateMember(MemberVO vo) {
		MemberVO m = dao.updateMember(vo);
		if(m!=null) {
			log.addLog("put", dao.getSql(), true);
		} else {
			log.addLog("put", dao.getSql(), false);
		}
		return m;
	}

	public boolean removeMember(Integer id) {
		boolean m = dao.removeMember(id);
		if(m==true) {
			log.addLog("delete", dao.getSql(), true);
		} else {
			log.addLog("delete", dao.getSql(), false);
		}
		return m;
	}
}
