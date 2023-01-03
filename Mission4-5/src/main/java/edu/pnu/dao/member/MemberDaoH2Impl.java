package edu.pnu.dao.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {

	private Connection con = null;
	
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission5", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> getMembers() {
		String sqlString = "select * from member order by id";
		
		Statement st = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			
			while(rs.next()) {
				list.add(new MemberVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("pass"),
						rs.getDate("regidate")
						));
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", list);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Map<String, Object> getMember(int id) {
		String sqlString = String.format("select * from member where id='%d'",id);
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPass(rs.getString("pass"));
			m.setRegidate(rs.getDate("regidate"));
			
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", m);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int getNextId() {
		String sqlString = "select max(id) from member";
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			return rs.getInt(1)+1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	public Map<String, Object> addMember(MemberVO vo) {
		int id = getNextId();
		String sqlString = String.format("insert into member(id, name, pass, regidate) values ('%d','%s','%s','%s')", 
				id, vo.getName(), vo.getPass(), new Date(System.currentTimeMillis()));
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getMember(id);
	}
	
	public Map<String, Object> updateMember(MemberVO vo) {
		int id = vo.getId();
		String sqlString = String.format("update member set name='%s', pass='%s' where id='%d'", 
				vo.getName(), vo.getPass(), id);
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getMember(id);
	}
	
	public Map<String, Object> removeMember(int id) {
		MemberVO m = (MemberVO) getMember(id).get("data");
		String sqlString = String.format("delete from member where id = '%d'", id);
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(st!=null)	st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sqlString);
		map.put("data", m);
		return map;
	}
}
