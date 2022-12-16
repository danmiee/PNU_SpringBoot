package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	List<MemberVO> list;

	// 초기 데이터 세팅
	public MemberService() {
		list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(new MemberVO(i, "name" + i, "1234", new Date()));
		}
	}

	public List<MemberVO> getMembers() {
		return list;
	}

	public MemberVO getMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	public MemberVO addMember(MemberVO vo) {
		vo.setId(list.size()+1);
		vo.setRegidate(new Date());
		list.add(vo);
		return vo;
	}

	// return 위치 주의
	public MemberVO updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				return m;
			}
		}
		return null;
	}

	// {} 및 return 위치 주의
	public MemberVO removeMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId()==id) {
				list.remove(m);
				return m;
			}
		}
			return null;
	}
}
