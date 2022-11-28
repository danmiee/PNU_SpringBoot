package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list;

	public MemberDaoListImpl() {
		super();
		list = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			list.add(new MemberVO(i, "pass" + i, "name" + i, new Date()));
		}
	}

	public List<MemberVO> getMembers() {
		return list;
	}

	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	public MemberVO addMember(MemberVO vo) {
		vo.setId(list.size());
		vo.setRegidate(new Date());
		list.add(vo);
		System.out.println("addMember Success");
		return vo;
	}

	public MemberVO updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				System.out.println("updateMember Success");
				return vo;
			}
		}
		return null;
	}

	public boolean removeMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				System.out.println("removeMember Success");
				return true;
			}
		}
		return false;
	}
	
}
