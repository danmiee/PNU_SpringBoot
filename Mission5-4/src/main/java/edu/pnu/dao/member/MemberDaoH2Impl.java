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

import edu.pnu.domain.MemberVO;

//@Repository
public class MemberDaoH2Impl implements MemberInterface {

	private Connection con = null;

	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission4", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		String sqlString = "select * from member order by id";

		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);

			while (rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(new Date(System.currentTimeMillis()));
				list.add(m);
			}

			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", list);

			return map;
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

	public Map<String, Object> getMember(Integer id) {
		String sqlString = String.format("select * from member where id='%d'", id);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();

			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setPass(rs.getString("pass"));
			m.setName(rs.getString("name"));
			m.setRegidate(new Date(System.currentTimeMillis()));

			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", m);

			return map;
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

	public int getMaxId() {
		String sqlString = "select max(id) from member";

		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			if (rs.next())
				// id값만 select 했기에 인덱스로 적어줌
				return rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Map<String, Object> addMember(MemberVO vo) {

		vo.setId(getMaxId());
		vo.setRegidate(new Date(System.currentTimeMillis()));
		String sqlString = String.format("insert into member(id, pass, name, regidate) values ('%d', '%s', '%s', '%s')",
				vo.getId(), vo.getPass(), vo.getName(), vo.getRegidate());

		Statement st = null;
		try {
			st = con.createStatement();

			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", vo);

			if (st.executeUpdate(sqlString) != 0)
				return map;

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

	public Map<String, Object> updateMember(MemberVO vo) {

		String sqlString = String.format("update member set name='%s', pass='%s' where id='%d'", vo.getName(),
				vo.getPass(), vo.getId());
		Statement st = null;

		try {
			st = con.createStatement();

			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", vo);

			if (st.executeUpdate(sqlString) != 0)
				return map;

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

	public Map<String, Object> removeMember(Integer id) {
		MemberVO m = (MemberVO) getMember(id).get("data");
		// query문 기억하기
		String sqlString = String.format("delete from member where id='%d'", id);
		Statement st = null;

		try {
			st = con.createStatement();

			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", m);
			
			if (st.executeUpdate(sqlString) != 0)
				return map;

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
