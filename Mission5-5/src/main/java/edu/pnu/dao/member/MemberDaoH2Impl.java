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

// query문이 정상 실행되지 않는 경우 로그 기록을 위한 처리
// query문 작성 시 '' 주의

//@Repository
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

			while (rs.next()) {
				list.add(new MemberVO(rs.getInt("id"), rs.getString("name"), rs.getString("pass"),
						rs.getDate("regidate")));
			}
			Map<String, Object> map = new HashMap<>();
			if (list.size()!=0) {
				map.put("sql", sqlString);
				map.put("data", list);
			} else {
				map.put("sql", sqlString);
				map.put("data", null);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Map<String, Object> getMember(int id) {
		String sqlString = String.format("select * from member where id=%d", id);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);

			// 모든 상황에서 map 리턴해야하므로 if문 밖에서 생성
			Map<String, Object> map = new HashMap<>();

			// data로 넣을 값이 있는 경우 해당 값 세팅하여 입력
			if (rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));

				map.put("sql", sqlString);
				map.put("data", m);
				// data로 넣을 값이 없으면 null 입력
			} else {
				map.put("sql", sqlString);
				map.put("data", null);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
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
			return rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	public Map<String, Object> addMember(MemberVO vo) {
		int id = getNextId();
		Statement st = null;

		try {
			vo.setRegidate(new Date(System.currentTimeMillis()));
			String sqlString = String.format(
					"insert into member(id, name, pass, regidate) values ('%d','%s','%s','%s')", id, vo.getName(),
					vo.getPass(), vo.getRegidate());

			st = con.createStatement();
			// 리턴할 맵 생성
			Map<String, Object> res = new HashMap<>();
			// 쿼리 정상 실행 시
			if (st.executeUpdate(sqlString) == 1) {
				// 쿼리 실행 결과 가져오기
				Map<String, Object> map = getMember(id);
				// 리턴내용 세팅
				res.put("sql", sqlString);
				res.put("data", map.get("data"));
				// 쿼리 미실행 시
			} else {
				// 리턴내용 세팅
				res.put("sql", sqlString);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Map<String, Object> updateMember(MemberVO vo) {
		String sqlString = String.format("update member set name='%s', pass='%s' where id=%d", vo.getName(),
				vo.getPass(), vo.getId());
		Statement st = null;

		try {
			st = con.createStatement();
			Map<String, Object> res = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(vo.getId());
				res.put("sql", sqlString);
				res.put("data", map.get("data"));
			} else {
				res.put("sql", sqlString);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Map<String, Object> removeMember(int id) {
		String sqlString = String.format("delete from member where id = %d", id);
		Statement st = null;

		try {
			st = con.createStatement();
			Map<String, Object> res = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(id);
				res.put("sql", sqlString);
				res.put("data", map.get("data"));
			} else {
				res.put("sql", sqlString);
				res.put("data", null);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
