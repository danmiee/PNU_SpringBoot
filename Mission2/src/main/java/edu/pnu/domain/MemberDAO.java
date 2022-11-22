package edu.pnu.domain;

import javax.servlet.ServletContext;
import edu.pnu.common.JDBConnect;

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
	public MemberVO getMembersVO() {
		MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
		String query = "SELECT * FROM member"; // 쿼리문 템플릿

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			rs = psmt.executeQuery(); // 쿼리문 실행

			// 결과 처리
			if (rs.next()) {
				// 쿼리 결과로 얻은 회원 정보를 vo 객체에 저장
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString(3));
				vo.setRegidate(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo; // vo 객체 반환
	}

	// 명시한 아이디와 일치하는 회원 정보를 반환합니다.
	public MemberVO getMemberVO(String uid) {
		MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
		String query = "SELECT * FROM member WHERE id=?"; // 쿼리문 템플릿

		try {
			// 쿼리 실행
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, uid); // 쿼리문의 첫 번째 인파라미터에 값 설정
			rs = psmt.executeQuery(); // 쿼리문 실행

			// 결과 처리
			if (rs.next()) {
				// 쿼리 결과로 얻은 회원 정보를 vo 객체에 저장
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegidate(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo; // vo 객체 반환
	}

	// member 추가
	public MemberVO addMemberVO() {
		MemberVO vo = new MemberVO(); // 회원 정보 vo 객체 생성
		String query = "INSERT INTO member VALUES (?, ?, ?, ?)"; // 쿼리문 템플릿
//		PreparedStatement psmt = con.prepareStatement(query);
//		psmt.setInt(1, id);
//		psmt.setString(2, pass);
//		psmt.setString(3, name);
//		psmt.setString(4, regidate);
		return vo; // vo 객체 반환
	}
}
