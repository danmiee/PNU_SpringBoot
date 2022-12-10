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

	private Connection con = null;
	
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission2", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getMembers() {
		Statement st = null;
		ResultSet rs = null;
		List <MemberVO> list = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from member order by id");
			while(rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberVO getMember(@PathVariable Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from member where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
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

	private int getNextId() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
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

		int id = getNextId();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(
					"insert into member(id, name, pass, regidate) values (?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPass());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.executeUpdate();
			
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
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update member set name=?, pass=? where id=?");
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
