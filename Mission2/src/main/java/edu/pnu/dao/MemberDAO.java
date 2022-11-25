package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import edu.pnu.common.JDBConnect;
import edu.pnu.domain.MemberVO;

public class MemberDAO extends JDBConnect {

	// 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDAO() {
		super();
	}

	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}

	public MemberDAO(ServletContext application) {
		super(application);
	}

	// 명시한 아이디와 일치하는 회원 정보를 반환합니다.
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM member"; // 쿼리문 템플릿
			PreparedStatement ps;
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
		}
		System.out.println("getMembers Success");
		return list; // vo 객체 반환
	}

	// 명시한 아이디와 일치하는 회원 정보를 반환합니다.
	public MemberVO getMember(String uid) {
		MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
		try {
			String query = "SELECT * FROM member WHERE id=?"; // 쿼리문 템플릿
			// 쿼리 실행
			PreparedStatement ps = con.prepareStatement(query); // 동적 쿼리문 준비
			ps.setString(1, uid); // 쿼리문의 첫 번째 인파라미터에 값 설정
			rs = ps.executeQuery(); // 쿼리문 실행

			// 결과 처리
			while (rs.next()) {
				if (rs.getString("id").equals(uid)) {
					// 쿼리 결과로 얻은 회원 정보를 vo 객체에 저장
					vo.setId(rs.getString("id"));
					vo.setPass(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegidate(rs.getDate(4));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getMember Success");
		return vo; // vo 객체 반환
	}

	// member 추가
	public MemberVO addMember(MemberVO vo) {
		vo.setRegidate(new Date());
		try {
			String query = "INSERT INTO member(id, pass, name) VALUES (?, ?, ?)"; // 쿼리문 템플릿
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("addMember Success");
		return vo; // vo 객체 반환
	}

	public MemberVO updateMember(MemberVO vo) {
		vo.setRegidate(new Date());
		try {
			String query = "UPDATE member set pass=?, name=? where id=?"; // 쿼리문 템플릿
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, vo.getPass());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("addMember Success");
		return vo; // vo 객체 반환
	}

	public MemberVO removeMember(String id) {
		String query = "DELETE FROM member where id=?"; /* SQL 문 */
		try {
			PreparedStatement ps = con.prepareCall(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("delete Success");
		return null;
	}
}
