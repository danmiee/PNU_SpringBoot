package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	List<MemberVO> list = new ArrayList<>();
	
	public MemberService() {
		for(int i=1; i<=10; i++) {
			list.add(new MemberVO(i, "pass"+i, "name"+i, new Date()));
		}
	}
	
	public List<MemberVO> getMembers(){
		return list;
	}
	
	public MemberVO getMember(Integer id) {
		for(int i=0; i<list.size(); i++) {
			// 인덱스와 id 불일치 주의
			if(i+1==id)
				return list.get(i);
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO vo) {
		int id = list.size()+1;
		vo.setId(id);
		vo.setRegidate(new Date());
		list.add(vo);
		return vo;
	}
	
	public MemberVO updateMember(MemberVO vo) {
		for(MemberVO m : list) {
			if(m.getId()==vo.getId()) {
				m.setName(vo.getName());
				m.setPass(vo.getPass());
				return m;
			}
		}
		return null;
	}
	
	public MemberVO removeMember(Integer id) {
		for(int i=0; i<list.size(); i++) {
			// 인덱스와 id 불일치 주의
			if(i+1==id) {
				MemberVO m = list.get(i);
				list.remove(i);
				return m;
			}
		}
		return null;
	}
	
}
