package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;

public class MemberService {

	// list는 모든 메소드에 대하여 계속 유지되어야 하므로 전역변수로 선언
	List<MemberVO> list;

	// 컨트롤러 실행 시 임시 데이터 생성
	public MemberService() {
		super();
		list = new ArrayList<>();
		// default 값으로 인한 오류를 방지하기 위해 id를 1부터 넣어줌
		for (int i = 1; i <= 20; i++) {
			list.add(new MemberVO(i, "pass" + i, "name" + i, new Date()));
		}
	}

	public List<MemberVO> getMembers() {
		// list 전체 반환
		return list;
	}

	public MemberVO getMember(Integer id) {
		// list 전체를 돌며 주어진 id와 같은 경우 반환
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	public MemberVO addMember(MemberVO vo) {
		// list에 추가하고 추가완료 문구노출
		// id 중복방지
		vo.setId(list.size());
		// 날짜 자동기재
		vo.setRegidate(new Date());
		list.add(vo);
		System.out.println("addMember Success");
		return vo;
	}

	public MemberVO updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			// id가 int형이므로 id를 기재하지 않으면 0으로 인식
			if (m.getId() == vo.getId()) {
				// id, date는 고정값
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				System.out.println("updateMember Success");
				return vo;
			}
		}
		return null;
	}

	public MemberVO removeMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				System.out.println("removeMember Success");
				return m;
			}
		}
		return null;
	}

}
