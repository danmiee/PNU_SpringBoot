package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list = new ArrayList<>();
	
	public MemberDaoListImpl() {
		for(int i=1; i<=10; i++) {
			list.add(new MemberVO(
					i, "이름"+i, "1234", new Date(System.currentTimeMillis())
					));
		}
	}
	
	public Map<String, Object> getMembers() {
		String sqlString = "Called getMembers()";
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", list);
		return map;
	}
	
	public Map<String, Object> getMember(int id) {
		String sqlString = String.format("Called getMember('%d')",id);
		for (int i=0; i<list.size(); i++) {
			if(i+1==id) {
				Map<String, Object> map = new HashMap<>();
				map.put("sql", sqlString);
				map.put("data", list.get(id));
				return map;
			}
		}
		return null;
	}

	public int getNextId() {
		if (list.size()!=0)		return list.size()+1;
		return 1;
	}
	
	public Map<String, Object> addMember(MemberVO vo) {
		String sqlString = "Called addMember()";
		
		vo.setId(getNextId());
		vo.setRegidate(new Date(System.currentTimeMillis()));
		list.add(vo);

		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", vo);
		return map;
	}
	
	public Map<String, Object> updateMember(MemberVO vo) {
		String sqlString = "Called updateMember()";
		for (int i=0; i<list.size(); i++) {
			if(i+1==vo.getId()) {
				MemberVO m = list.get(i);
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
	
	public Map<String, Object> removeMember(int id) {
		String sqlString = "Called removeMember()";
		for (int i=0; i<list.size(); i++) {
			if(i+1==id) {
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
