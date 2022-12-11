package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

// 양자택일(MemberDaoH2Impl, MemberDaoListImpl) 필요로 주석처리
//@Repository
public class MemberDaoListImpl implements MemberInterface {
	List<MemberVO> list;

	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(new MemberVO(i, "name" + i, "1234", new Date()));
		}
	}

	 /* MemberDaoListImpl 로그기록 idea
	 * - Map함수 객체 생성 및 로그 기록할 내용 저장
	 *   - "sql" : sql(query문 영역 적절히 임의기재)
	 *   - "data" : 실행결과
	 */
	
	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		map.put("sql", "from list getMembers()");
		map.put("data", list);
		return map;
	}

	public Map<String, Object> getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				Map<String, Object> map = new HashMap<>();
				map.put("sql", "from list getMember()");
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
		map.put("sql", "from list addMember()");
		map.put("data", vo);
		
		return map;
	}

	public Map<String, Object> updateMember(MemberVO vo) {
		for (MemberVO m : list) {
			if (m.getId() == vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());

				Map<String, Object> map = new HashMap<>();
				map.put("sql", "form list updateMember()");
				map.put("data", m);

				return map;
			}
		}
		return null;
	}

	public Map<String, Object> delMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);

				Map<String, Object> map = new HashMap<>();
				map.put("sql", "form list delMember()");
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
}
