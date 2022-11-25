package edu.pnu.dao.member;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import edu.pnu.common.JDBConnect;
import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl extends JDBConnect implements MemberDAO {

	private String sql;
	private PreparedStatement ps;
	private Statement st;
	
	// 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDaoH2Impl() {
		super();
	}

	public MemberDaoH2Impl(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}

	public MemberDaoH2Impl(ServletContext application) {
		super(application);
	}

	// 명시한 아이디와 일치하는 회원 정보를 반환합니다.
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM member"; // 쿼리문 템플릿
			sql = query;
			// 쿼리 실행
			ps = con.prepareStatement(query); // 동적 쿼리문 준비
			rs = ps.executeQuery(); // 쿼리문 실행

			// 결과 처리
			while (rs.next()) {
				MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
				// 쿼리 결과로 얻은 회원 정보를 vo 객체에 저장
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString(3));
				vo.setRegidate(rs.getDate(4));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("getMembers Success");
		return list; // vo 객체 반환
	}

	// 명시한 아이디와 일치하는 회원 정보를 반환합니다.
	public MemberVO getMember(String uid) {
		//MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
		MemberVO vo = null;
		try {
			String query = "SELECT * FROM member WHERE id=?"; // 쿼리문 템플릿
			// 쿼리 실행
			sql = String.format("SELECT * FROM member WHERE id='%s'", uid);
			ps = con.prepareStatement(query); // 동적 쿼리문 준비
			ps.setString(1, uid); // 쿼리문의 첫 번째 인파라미터에 값 설정
			rs = ps.executeQuery(); // 쿼리문 실행
			// 결과 처리
			while (rs.next()) {
				if (rs.getString("id").equals(uid)) {
					vo = new MemberVO();
					// 쿼리 결과로 얻은 회원 정보를 vo 객체에 저장
					vo.setId(rs.getString("id"));
					vo.setPass(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegidate(rs.getDate(4));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("getMember Success");
		return vo; // vo 객체 반환
	}

	// member 추가
	public MemberVO addMember(MemberVO vo) {
		vo.setRegidate(new Date());
		try {
//			String query = "INSERT INTO member(id, pass, name) VALUES (?, ?, ?)"; // 쿼리문 템플릿
//			ps = con.prepareStatement(query);
//			ps.setString(1, vo.getId());
//			ps.setString(2, vo.getPass());
//			ps.setString(3, vo.getName());
//			if(ps.executeUpdate()==0) {
//				return null;
//			};
			
			// log로 불러오기 위한 query string
			sql = String.format("INSERT INTO member(id, pass, name) VALUES ('%s','%s','%s')",
					vo.getId(), vo.getPass(), vo.getName());
			if (vo.getId()==null) {
				return null;
			}
			st = con.createStatement();
			
			// sql이 없으면 null 반환
			// executeUpdate는 리턴값이 없을 때 0을 반환함
			if (st.executeUpdate(sql) == 0) {
				return null;
			}
		} catch (SQLException e) {
			// 에러일 때도 null 반환
			e.printStackTrace();
			return null;
		}
		System.out.println("addMember Success");
		return vo; // vo 객체 반환
	}

	public MemberVO updateMember(MemberVO vo) {
		vo.setRegidate(new Date());
		try {
//			String query = "UPDATE member set pass=?, name=? where id=?"; // 쿼리문 템플릿
//			ps = con.prepareStatement(query);
//			ps.setString(1, vo.getPass());
//			ps.setString(2, vo.getName());
//			ps.setString(3, vo.getId());
//			ps.executeUpdate();
			
			// log로 불러오기 위한 query string
			sql = String.format("UPDATE member set pass='%s', name='%s' where id='%s'",
					vo.getPass(), vo.getName(), vo.getId());
			
			st = con.createStatement();
			
			if(st.executeUpdate(sql)==0) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("updateMember Success");
		return vo; // vo 객체 반환
	}

	public boolean removeMember(String id) {
//		String query = "DELETE FROM member where id=?"; /* SQL 문 */
		try {
//			ps = con.prepareCall(query);
//			ps.setString(1, id);
//			ps.executeUpdate();
			
			sql = String.format("DELETE FROM member where id='%s'", id);
			st = con.createStatement();
			if(st.executeUpdate(sql)!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("removeMember Success");
		return false;
	}
	
	public String getSql() {
		return sql;
	}
}
