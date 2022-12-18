package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

	List<MemberVO> list;

	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(new MemberVO(i, "name" + i, "1234", new Date()));
		}
	}

	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		map.put("sql", "from getMembers()");
		map.put("data", list);
		return map;
	}

	public Map<String, Object> getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				Map<String, Object> map = new HashMap<>();
				map.put("sql", String.format("from getMember(%d)",id));
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

	public Map<String, Object> addMember(MemberVO vo) {
		vo.setId(list.size() + 1);
		vo.setRegidate(new Date());
		list.add(vo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", String.format("from addMember(%d)", vo.getId()));
		map.put("data", vo);
		return map;
	}

	public Map<String, Object> updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				
				Map<String, Object> map = new HashMap<>();
				map.put("sql", String.format("from updateMember(%d)", vo.getId()));
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

	public Map<String, Object> removeMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				
				Map<String, Object> map = new HashMap<>();
				map.put("sql", String.format("from removeMember(%d)", id));
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
}