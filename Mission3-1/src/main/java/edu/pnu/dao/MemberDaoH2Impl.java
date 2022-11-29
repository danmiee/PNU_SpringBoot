package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {
	// DB와 연결하는 connection 생성
	private Connection con = null;
	
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission1","sa","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// select * from table
	public List<MemberVO> getMembers() {
		// 결과값 저장할 list 선언
		List<MemberVO> list = new ArrayList<>();
		// sql문 실행하는 객체 생성
		Statement st = null;
		// sql문 실행 결과 받는 ResultSet 생성
		ResultSet rs = null;
		try {
			// con에서 객체 부여
			String sql = "select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			// rs의 다음값이 있으면 list에 rs 추가
			while (rs.next()) {
				// list에 넣을 MemberVO 객체 m 선언
				MemberVO m = new MemberVO();
				// rs가 가진 값을 m에 넣어주기
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("getMembers: " + list);
		return list;
	}

	public MemberVO getMember(Integer id) {
		MemberVO m = new MemberVO();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where id=?";
			// sql문 실행 객체
			ps = con.prepareStatement(sql);
			// query문의 파라미터 값 설정
			ps.setInt(1,id);
			// sql문 실행 결과
			rs = ps.executeQuery();
			// 다음 rs가 있는 동안 반복문 돌면서 입력id와 같은 값 찾기
			while (rs.next()) {
				if (rs.getInt("id")==id) {
					m.setId(rs.getInt("id"));
					m.setName(rs.getString("name"));
					m.setPass(rs.getString("pass"));
					m.setRegidate(rs.getDate("regidate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println("getMember: " + m);
		return m;
	}

	// 최댓값 다음 수를 찾아주는 메소드
	private int getNextId() {
		// sql문 실행 객체
		Statement st = null;
		// sql문 실행 결과
		ResultSet rs = null;
		
		try {
			// id열 중 최댓값을 구하는 쿼리문
			String sql = "select max(id) from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			// rs : max값 앞을 가리킴
			// rs.next() : max값을 가리킴
			rs.next();
			// rs 내 요소 1개 > rs의 첫번째 요소(max값)를 가져와서 +1
			return rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	public MemberVO addMember(MemberVO vo) {
		PreparedStatement ps = null;
		
		// id 중복방지를 위하여 기존에 저장된 아이디의 최대값 다음 수가 자동으로 매핑되게 함
		int id = getNextId();
		vo.setId(id);
		
		try {
			String sql = "insert into member(id,name,pass,regidate) values (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPass());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("addMember: " + vo);
		return vo;
	}

	public MemberVO updateMember(MemberVO vo) {
		PreparedStatement ps = null;
		
		try {
			String sql = "update member set name=?, pass=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPass());
			ps.setInt(3, vo.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("updateMember: " + vo);
		return vo;
	}

	// 리턴형태 boolean으로 변경
	public boolean removeMember(Integer id) {
		PreparedStatement ps = null;
		try {
			String sql = "delete from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			if (ps.executeUpdate() == 1) {
				System.out.println("deleteMember id: " + id);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("No search id");
		return false;
	}
	
}
