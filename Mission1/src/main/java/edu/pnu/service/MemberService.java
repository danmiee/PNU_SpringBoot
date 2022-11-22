package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {

	private List<MemberVO> list;

	public MemberService() {
		list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new MemberVO(i, "1234", "이름" + i, new Date()));
		}
	}

	// 모든 멤버 정보를 JSON 형태로 브라우저에 출력
	public List<MemberVO> getMembers() {
		return list;
	}

	// 아이디가 {id} 인 member를 찾아서 브라우저에 출력
	public MemberVO getMember(int id) {
		// 아이디가 {id}인 member 검색
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				// 해당 값 출력
				return list.get(i);
			}
		}
		return null;
	}

	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO vo) {
		// id 및 regidate 자동세팅
		vo.setId(list.size());
		vo.setRegidate(new Date());
		// list에 내용 추가
		list.add(vo);
		// 추가된 객체 출력
		int id = vo.getId();
		return list.get(id);
	}

	// 수정 대상 객체 정보를 전달, 수정된 객체를 출력
	public MemberVO updateMembers(MemberVO vo) {
		// regidate 자동세팅
		vo.setRegidate(new Date());
		// 수정 대상 객체 id를 기준으로 정보 수정
		int id = vo.getId();
		list.set(id, vo);
		return list.get(id);
	}

	// 아이디가 {id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	public MemberVO removeMembers(int id) {
		// 아이디가 {id}인 member 찾기
		MemberVO deleted = new MemberVO();
		
		for (int i = 0; i < list.size(); i++) {
			// 오버라이딩 사용
			if(list.get(i).equals(deleted)) {
			// 오버라이딩 미사용
//			if (list.get(i).getId() == id) {
				// 출력 위해 별도 저장
				deleted = list.get(i);
				// 검색된 객체 제거
				list.remove(i);
			}
		}
		
		// 출력
		return deleted;
	}
}
