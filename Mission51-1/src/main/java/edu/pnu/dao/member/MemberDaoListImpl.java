package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import edu.pnu.domain.MemberVO;

//@Repository
@SuppressWarnings("unused")
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

	public MemberVO removeMember(Integer id) {
		sqlString = "removeMember()";
		MemberVO m = new MemberVO();
		for (MemberVO vo : list) {
			if (vo.getId() == id) {
				m = vo;
				list.remove(vo);
				System.out.println("removeMember Success");
				return m;
			}
		}
		return null;
	}

	@Override
	public String getSqlString() {
		return sqlString;
	}
	
}
