package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list;

	public MemberDaoListImpl() {
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

	public MemberVO addMember(MemberVO vo) {
		vo.setId(list.size() + 1);
		vo.setRegidate(new Date());
		list.add(vo);
		return vo;
	}

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

	public MemberVO delMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return m;
			}
		}
		return null;
	}

}
