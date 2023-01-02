package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberService {

	// 멤버 저장할 list 전역변수로 선언
	List<MemberVO> list;

	// 임시데이터 넣어주기
	public MemberService() {
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(new MemberVO(i, "name" + i, "1234", new Date()));
		}
	}

	public List<MemberVO> getMembers() {
		return list;
	}

	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public MemberVO postMember(MemberVO vo) {
		// 추가하는 요소의 id가 기존값과 겹치지 않도록 자동 배정
		vo.setId(list.size() + 1);
		// date 자동 기재
		vo.setRegidate(new Date());
		// id, date 세팅된 vo를 list에 추가
		list.add(vo);
		return vo;
	}

	public MemberVO updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				// id, date는 유지하며 name, pass만 변경
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				return m;
			}
		}
		return null;
	}

	public MemberVO delMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				// 제거 후에도 m값 리턴 가능
				return m;
			}
		}
		return null;
	}

}
