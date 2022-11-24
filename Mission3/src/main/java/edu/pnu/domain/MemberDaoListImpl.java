package edu.pnu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDaoListImpl implements MemberDAO {

	private List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new MemberVO(""+i, "1234", "name" + i, new Date()));
		}
	}

	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(String uid) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(uid)) {
				// 해당 값 출력
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO vo) {
		int idx = list.size();
		vo.setId(""+idx);
		vo.setRegidate(new Date());
		list.add(vo);
		return list.get(idx);
	}

	@Override
	public MemberVO updateMember(MemberVO vo) {
		vo.setRegidate(new Date());
		int idx = Integer.parseInt(vo.getId());
		list.set(idx, vo);
		return list.get(idx);
	}

	@Override
	public MemberVO removeMember(String id) {
		MemberVO deleted = new MemberVO();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(deleted)) {
				deleted = list.get(i);
				list.remove(i);
			}
		}
		return deleted;
	}

}
