package edu.pnu.service;

import java.util.Date;
import java.util.List;

import edu.pnu.domain.LogDAO;
import edu.pnu.domain.MemberDAO;
import edu.pnu.domain.MemberDaoH2Impl;
import edu.pnu.domain.MemberDaoListImpl;
import edu.pnu.domain.MemberVO;

@SuppressWarnings("unused")
public class MemberService {

	MemberDAO dao = new MemberDaoH2Impl();
//	MemberDAO dao = new MemberDaoListImpl();
	LogDAO log = new LogDAO();
	
	// 모든 멤버 정보를 JSON 형태로 브라우저에 출력
	public List<MemberVO> getMembers() {
		List<MemberVO> m = dao.getMembers();
		if (m!=null) {
			log.addLog("get", dao.getSql(), System.currentTimeMillis(), true);
			return m;
		}
		log.addLog("get", dao.getSql(), System.currentTimeMillis(), false);
		return null;
	}

	// 아이디가 {id} 인 member를 찾아서 브라우저에 출력
	public MemberVO getMember(String id) {
		MemberVO m = dao.getMember(id);
		if (m!=null) {
			log.addLog("get", dao.getSql(), System.currentTimeMillis(), true);
			return m;
		}
		log.addLog("get", dao.getSql(), System.currentTimeMillis(), false);
		return null;
	}
	
	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO vo) {
		// 쿼리 실행값을 받아오는 객체 생성
		MemberVO m = dao.addMember(vo);
		if (m!=null) {
			// 객체가 null이 아니면 true 로그 기록 및 객체 반환
			log.addLog("post", dao.getSql(), System.currentTimeMillis(), true);
			return m;
		}
		// 객체가 null이면 false 로그 기록 및 null 반환
		log.addLog("post", dao.getSql(), System.currentTimeMillis(), false);
		return null;
	}

	// 수정 대상 객체 정보를 전달, 수정된 객체를 출력
	public MemberVO updateMember(MemberVO vo) {
		MemberVO m = dao.updateMember(vo);
		if (m!=null) {
			log.addLog("put", dao.getSql(), System.currentTimeMillis(), true);
			return m;
		}
		log.addLog("put", dao.getSql(), System.currentTimeMillis(), false);
		return null;
	}

	// 아이디가 {id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	public boolean removeMember(String id) {
		boolean m = dao.removeMember(id);
		if (m) {
			log.addLog("delete", dao.getSql(), System.currentTimeMillis(), true);
			return m;
		}
		log.addLog("delete", dao.getSql(), System.currentTimeMillis(), false);
		return m;
	}
}
