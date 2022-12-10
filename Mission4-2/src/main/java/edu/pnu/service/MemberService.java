package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDaoH2Impl;
import edu.pnu.dao.log.LogInterface;
import edu.pnu.dao.member.MemberDaoH2Impl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	/*
	 * logDao 구현 idea
	 * 1. 메소드명과 결과값을 담는 Map함수 객체 생성
	 * 2. 1의 객체에서 data 얻기
	 * 3. data가 null이 아닐 때 성공여부에 따라 로그 기록하기
	 */
	
	private MemberInterface memberDao;
	private LogInterface logDao;
	
	public MemberService() {
		this.memberDao = new MemberDaoH2Impl();
//		this.memberDao = new MemberDaoListImpl();
		this.logDao = new LogDaoH2Impl();
	}
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}

	public MemberVO addMember(MemberVO vo) {
		return memberDao.addMember(vo);
	}

	public MemberVO updateMember(MemberVO vo) {
		return memberDao.updateMember(vo);
	}

	public MemberVO delMember(Integer id) {
		return memberDao.delMember(id);
	}

}
