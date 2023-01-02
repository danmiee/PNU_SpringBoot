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
import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

@Repository
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
	
	/*
	 * MemberDaoH2Impl 로그기록 idea
	 * 1. query문 분리
	 *   - PreparedStatement > Statement(with String.format)
	 * 2. Map함수 객체 생성 및 로그 기록할 내용 저장
	 *   - "sql" : sql
	 *   - "data" : query문 실행결과
	 */
	
	public Map<String, Object> getMembers() {
		Statement st = null;
		ResultSet rs = null;
		// log 기록을 위해 query문 변수로 별도 저장
		String sql = "select * from member order by id";
		try {
			List <MemberVO> list = new ArrayList<>();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
			// map 객체 생성 및 로그 기록할 내용 저장
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", list);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Map<String, Object> getMember(@PathVariable Integer id) {
		// sql문 분리를 위해 format을 활용한 Statement 사용
		String sql = String.format("select * from member where id='%d'",id);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPass(rs.getString("pass"));
			m.setRegidate(rs.getDate("regidate"));
			
			// map 객체 생성 및 로그 기록할 내용 저장
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", m);
			
			return map;
		} catch(Exception e) {
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
	
	public Map<String, Object> addMember(MemberVO vo) {

		int id = getNextId();
		// query문 분리 목적으로 ps > st
		Statement st = null;
		try {
			// query문 별도 저장
			// String.format 사용 시 변수 넣을 곳에 '' 필수
			String sql = String.format("insert into member(id,name,pass,regidate) values ('%d', '%s', '%s', '%s')",
					id, vo.getName(), vo.getPass(), new Date(System.currentTimeMillis()));
			st = con.createStatement();
			
			// 로그 기록할 Map 객체 
			Map<String, Object> res = new HashMap<>();
			// query문 실행 성공 시 결과값 id로 불러와서 저장
			if(st.executeUpdate(sql)==1) {
				Map<String, Object> map = getMember(id);
				res.put("sql", sql);
				res.put("data", map.get("data"));
			}
			// query문 실행 실패 시 결과값 null로 저장
			else {
				res.put("sql", sql);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public Map<String, Object> updateMember(MemberVO vo) {
		// ps > st
		Statement st = null;
		try {
			// query문 분리
			String sql = String.format("update member set name='%s', pass='%s' where id='%d'", 
					vo.getName(), vo.getPass(), vo.getId());
			st = con.createStatement();
			// Map 객체 생성
			Map<String, Object> res = new HashMap<>();
			// query문 실행 성공 시 결과값 id로 가져와서 저장
			if(st.executeUpdate(sql)==1) {
				Map<String, Object> map = getMember(vo.getId());
				res.put("sql", sql);
				res.put("data", map.get("data"));
			}
			// query문 실행 실패 시 결과값 null로 저장
			else {
				res.put("sql", sql);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Map<String, Object> delMember(@PathVariable Integer id) {
		// ps > st
		Statement st = null;
		try {
			String sql = String.format("delete from member where id='%d'",id);
			st = con.createStatement();
			
			// id의 내용을 map으로 저장
			Map<String, Object> map = getMember(id);
			
			// 저장된 내용(지울 내용)이 없으면 null 리턴
			if (map.get("data")==null)
				return null;
			
			// 로그용 map 객체 생성
			Map<String, Object> res = new HashMap<>();
			// query문 실행 성공 시 query문과 결과값 map에서 가져와서 저장
			if(st.executeUpdate(sql) == 1) {
				res.put("sql", sql);
				res.put("data", map.get("data"));
			}
			// query문 실행 실패 시 query문과 결과값 null 저장
			else {
				res.put("sql", sql);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
