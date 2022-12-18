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

public class MemberDaoH2Impl implements MemberInterface {

	// JDBC Connection 호출
	private Connection con;

	// 기본생성자 - JDBC 드라이버 로드
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> getMembers() {
		String sqlString = "select * from member order by id";
		Statement st = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			
			while(rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
			return list;
			
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
		return null;
	}

	public MemberVO getMember(@PathVariable Integer id) {
		String sqlString = "select * from member where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(sqlString);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			
			// rs에 저장된 값으로 MemberVO 객체 셋팅하기
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPass(rs.getString("pass"));
			m.setRegidate(rs.getDate("regidate"));
			
			return m;
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
		return null;
	}

	// id 최대값으로 자동 부여 
	private int getNextId() {
		String sqlString = "select max(id) from member";
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			if(rs.next())
				return rs.getInt(1)+1;
			else
				return 1;
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
		return 0;
	}
	
	public MemberVO addMember(MemberVO vo) {
		String sqlString = "insert into member(id, name, pass, regidate) values (?, ?, ?, ?)";
		PreparedStatement ps = null;
//		vo.setId(getNextId());
		// id로 바로 사용 가능
		int id = getNextId();
		
		try {
			ps = con.prepareStatement(sqlString);
//			ps.setInt(1, vo.getId());
			ps.setInt(1, id);
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPass());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.executeUpdate();
//			return getMember(vo.getId());
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

	public MemberVO updateMember(MemberVO vo) {
		String sqlString = "update member set name=?, pass=? where id=?";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sqlString);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPass());
			ps.setInt(3, vo.getId());
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

	public MemberVO removeMember(@PathVariable Integer id) {
		String sqlString = "delete from member where id=?";
		PreparedStatement ps = null;
		MemberVO m = getMember(id);
		
		try {
			ps = con.prepareStatement(sqlString);
			ps.setInt(1, id);
			ps.executeUpdate();
			return m;
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
