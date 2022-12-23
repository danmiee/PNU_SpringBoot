package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

//@Repository
public class MemberDaoListImpl implements MemberInterface {
	
	List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			list.add(new MemberVO(i, "pass"+i, "name"+i, new Date()));
		}
	}
	
	public Map<String, Object> getMembers(){
		Map<String, Object> map = new HashMap<>();
		String sqlString = "getMembers()";
		map.put("sql", sqlString);
		map.put("data", list);
		return map;
	}
	
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		String sqlString = String.format("getMember('%d')", id);
		map.put("sql", sqlString);
		
		for(int i=0; i<list.size(); i++) {
			if(i+1==id) {
				map.put("data", list.get(i));
				return map;
			}
		}
		return null;
	}
	
	public Map<String, Object> addMember(MemberVO vo) {
		int id = list.size()+1;
		String sqlString = String.format("addMember('%d')", id);
				
		vo.setId(id);
		vo.setRegidate(new Date());
		list.add(vo);

		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", vo);
		
		return map;
	}
	
	public Map<String, Object> updateMember(MemberVO vo) {
		Map<String, Object> map = new HashMap<>();
		String sqlString = String.format("updateMember('%d')", vo.getId());
		map.put("sql", sqlString);
		
		for(MemberVO m : list) {
			if(m.getId()==vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
	
	public Map<String, Object> removeMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		String sqlString = String.format("removeMember('%d')", id);
		map.put("sql", sqlString);
		
		for(int i=0; i<list.size(); i++) {
			if(i+1==id) {
				MemberVO m = list.get(i);
				list.remove(i);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
	
}
