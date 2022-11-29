package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list;
	// 로그에 남길 sqlString을 담는 변수 선언
	private String sqlString;

	public MemberDaoListImpl() {
		super();
		list = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			list.add(new MemberVO(i, "pass" + i, "name" + i, new Date()));
		}
	}

	public List<MemberVO> getMembers() {
		// sql문이 없으므로 요청방식에 따라 요청내용 임의로 삽입
		sqlString = "getMembers()";
		return list;
	}

	public MemberVO getMember(Integer id) {
		sqlString = "getMember()";
		for (MemberVO m : list) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public MemberVO addMember(MemberVO vo) {
		sqlString = "addMember()";
		vo.setId(list.size());
		vo.setRegidate(new Date());
		list.add(vo);
		System.out.println("addMember Success");
		return vo;
	}

	public MemberVO updateMember(MemberVO vo) {
		sqlString = "updateMember()";
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
		sqlString = "removeMember()";
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				System.out.println("removeMember Success");
				return true;
			}
		}
		return false;
	}

	@Override
	public String getSql() {
		return sqlString;
	}
	
}
