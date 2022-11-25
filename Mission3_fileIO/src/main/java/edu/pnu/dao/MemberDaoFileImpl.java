package edu.pnu.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import edu.pnu.domain.MemberVO;

public class MemberDaoFileImpl implements MemberDAO {

	private List<MemberVO> list;
	
	public MemberDaoFileImpl() {
		list = new ArrayList<>();
		// list.txt에서 읽어오기
		try (BufferedReader br = new BufferedReader(new FileReader("list.txt"))) {
			String str;
			// readLine : 한줄씩 읽기
			while((str = br.readLine())!=null) {
				// 1줄 읽는동안 text 자르기
				StringTokenizer st = new StringTokenizer(str, ",");
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				String s3 = st.nextToken();
				// 읽은 내용 list에 memberVO로 넣어주기
				list.add(new MemberVO(s1, s2, s3, new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
