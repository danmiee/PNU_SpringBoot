package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list;

	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(new MemberVO(i, "pass" + i, "name" + i, new Date()));
		}
	}

	public Map<String, Object> getMembers() {
		String sqlString = "result of getMembers()";
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", list);
		return map;
	}

	public Map<String, Object> getMember(Integer id) {
		String sqlString = String.format("result of getMember('%d')",id);
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == id) {
				Map<String, Object> map = new HashMap<>();
				map.put("sql", sqlString);
				map.put("data", list.get(i));
				return map;
			}
		}
		return null;
	}

	public Map<String, Object> addMember(MemberVO vo) {
		int id = list.size() + 1;
		vo.setId(id);
		vo.setRegidate(new Date());
		list.add(vo);

		String sqlString = String.format("result of addMember('%d')",id);
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", vo);
		return map;
	}

	public Map<String, Object> updateMember(MemberVO vo) {
		String sqlString = String.format("result of updateMembers('%d')",vo.getId());
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());

				Map<String, Object> map = new HashMap<>();
				map.put("sql", sqlString);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

	public Map<String, Object> removeMember(Integer id) {
		String sqlString = String.format("result of getMembers('%d')",id);
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == id) {
				MemberVO m = list.get(i);
				list.remove(i);

				Map<String, Object> map = new HashMap<>();
				map.put("sql", sqlString);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

}
