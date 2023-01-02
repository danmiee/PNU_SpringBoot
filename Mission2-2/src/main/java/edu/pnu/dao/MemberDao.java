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

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberDao {

	// JDBC Connection 호출
	private Connection con = null;
	
	// 기본생성자 - JDBC 드라이버 로드
	public MemberDao() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission2", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// select * from member
	public List<MemberVO> getMembers() {
		// query문 매개변수 고정되어 있으므로 statement 변수 선언
		Statement st = null;
		// select문은 resultset 필요
		ResultSet rs = null;
		// 출력할 list 변수 선언
		List <MemberVO> list = new ArrayList<>();
		
		try {
			// sql문을 db로 전송하는 변수 선언
			st = con.createStatement();
			// st를 통해 query문 전송하고 결과값 rs에 저장
			rs = st.executeQuery("select * from member order by id");
			// rs가 존재하는동안 반복
			while(rs.next()) {
				// 새로운 객체 생성하여 rs에 저장된 값 넣어주기
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
				// 출력할 list에 추가
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 메모리 누수 방지 차 열었던 것 닫기
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// select * from member where id=?
	public MemberVO getMember(@PathVariable Integer id) {
		// query문 매개변수가 id로 계속 변하므로 preparedstatement 변수 선언
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ps를 통해 전송할 쿼리문 셋팅
			ps = con.prepareStatement("select * from member where id=?");
			// ps에 들어갈 매개변수 삽입
			ps.setInt(1, id);
			// 쿼리문 전송 및 결과값 rs로 저장
			rs = ps.executeQuery();
			// 저장된 값 가리키기
			rs.next();
			
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPass(rs.getString("pass"));
			m.setRegidate(rs.getDate("regidate"));
			return m;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 추가하는 요소의 id가 기존값과 겹치지 않도록 자동 배정
	// select max(id) from member
	private int getNextId() {
		// query문 내 변하는 매개변수 없음 > st 선언
		Statement st = null;
		// 전송할 query = select > rs 선언
		ResultSet rs = null;
		try {
			// query 전송 준비
			st = con.createStatement();
			// st로 query 전송 및 결과 저장
			rs = st.executeQuery("select max(id) from member");
			// max값 가리키기
			rs.next();
			// member에 존재하는 id 중 최대값 + 1을 리턴
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
		// rs.getInt(1)이 없다면 member에 존재하는 값이 없으므로 id 1부터 시작
		return 1;
	}
	
	// insert into member(id, name, pass, regidate) values (?, ?, ?, ?)
	public MemberVO addMember(MemberVO vo) {

		// id 자동입력
		int id = getNextId();
		
		// query문 내 매개변수 변동 O > ps
		PreparedStatement ps = null;
		
		try {
			// query문 및 매개변수 세팅
			ps = con.prepareStatement(
					"insert into member(id, name, pass, regidate) values (?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPass());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			// query문 전송
			ps.executeUpdate();
			
			// 추가한 정보 리턴 - getMember 메소드로 가져올 수 있음
			return getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	// update member set name=?, pass=? where id=? 
	public MemberVO updateMember(MemberVO vo) {
		PreparedStatement ps = null;
		try {
			// query문 준비
			ps = con.prepareStatement("update member set name=?, pass=? where id=?");
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPass());
			ps.setInt(3, vo.getId());
			// query문 전송
			ps.executeUpdate();
			
			return getMember(vo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// delete from member where id=?
	public MemberVO delMember(@PathVariable Integer id) {
		PreparedStatement ps = null;
		MemberVO vo = getMember(id);
		try {
			ps = con.prepareStatement("delete from member where id=?");
			ps.setInt(1, id);
			if(ps.executeUpdate() == 1) {
				return vo;
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
		return null;
	}
}
