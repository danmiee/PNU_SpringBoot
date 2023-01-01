package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list = new ArrayList<>();
	
	public MemberDaoListImpl() {
		for(int i=1; i<=10; i++) {
			list.add(new MemberVO(
					i, "이름"+i, "1234", new Date(System.currentTimeMillis())
					));
		}
	}
	
	public List<MemberVO> getMembers() {
		return list;
	}
	
	public MemberVO getMember(int id) {
		for (int i=0; i<list.size(); i++) {
			if(i+1==id)		return list.get(i);
		}
		return null;
	}

	public int getNextId() {
		if (list.size()!=0)		return list.size()+1;
		return 1;
	}
	
	public MemberVO addMember(MemberVO vo) {
		vo.setId(getNextId());
		vo.setRegidate(new Date(System.currentTimeMillis()));
		list.add(vo);
		return vo;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		for (int i=0; i<list.size(); i++) {
			if(i+1==vo.getId()) {
				MemberVO m = list.get(i);
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				return m;
			}
		}
		return null;
	}
	
	public MemberVO removeMember(int id) {
		for (int i=0; i<list.size(); i++) {
			if(i+1==id) {
				MemberVO m = list.get(i);
				list.remove(i);
				return m;
			}
		}
		return null;
	}
}
