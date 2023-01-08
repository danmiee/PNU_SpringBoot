package edu.pnu.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {

	// Connection 객체를 jdbcTemplate 객체로 변경
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDaoH2Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		/*
		 * <jdbcTemplate 메소드> queryForObject : 1개의 결과를 갖는 select 실행 시 사용 query : 여러 개의
		 * 결과를 갖는 select 실행 시 사용 update : insert, update, delete 실행 시 사용
		 */
	}

	public Map<String, Object> getMembers() {
		// 실행할 쿼리문
		String sqlString = "select * from member order by id";
		// 리턴할 맵 생성
		Map<String, Object> map = new HashMap<>();
		// 리턴 맵에 쿼리문 저장
		map.put("sql", sqlString);

		try {
			// 여러 개의 결과값을 처리해야하므로 jdbcTemplate.query 사용
			List<MemberVO> list = jdbcTemplate.query(
				// table 형태의 sqlString 실행 결과를 MemberVO 타입에 맞춰 형변환
				sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
			// 리턴 맵에 결과값 저장
			map.put("data", list);
		} catch (Exception e) {
			// 예외 발생 시 결과값 null로 저장
			map.put("data", null);
		}
		return map;
	}

	public Map<String, Object> getMember(int id) {
		// 실행할 쿼리문
		String sqlString = String.format("select * from member where id=%d", id);
		// 리턴할 맵 생성
		Map<String, Object> map = new HashMap<>();
		// 리턴 맵에 쿼리문 저장
		map.put("sql", sqlString);

		try {
			// 결과값이 한 개이므로 jdbcTemplate.queryForObject 사용
			MemberVO m = jdbcTemplate.queryForObject(
				// sqlString 실행 결과를 MemberVO 타입으로 가져오기
				// 다른 클래스를 반환 받는 것이므로 RowMapper 활용 필요
				// BeanPropertyRowMapper는 RowMapper를 오버라이딩한 것들 중 하나
				sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
			// 리턴 맵에 결과값 저장
			map.put("data", m);
		} catch (Exception e) {
			// 예외 발생 시 결과값 null로 저장
			map.put("data", null);
		}
		return map;
	}

	public int getNextId() {
		// 실행할 쿼리문
		String sqlString = "select max(id) from member";
		try {
			// sqlString 실행 결과를 Integer 타입으로 가져오기
			int max = jdbcTemplate.queryForObject(sqlString, Integer.class);
			// max+1을 다음 id로 리턴
			return max + 1;
		} catch (Exception e) {
			// 예외 발생 시 기록된 id가 없으므로 다음 id로 1 리턴
			return 1;
		}
	}

	public Map<String, Object> addMember(MemberVO vo) {
		// 추가할 member id 부여
		int id = getNextId();
		// 실행할 쿼리문
		String sqlString = String.format("insert into member(id, name, pass) values ('%d','%s','%s')",
				id, vo.getName(), vo.getPass());
		// 리턴할 맵 생성
		Map<String, Object> res = new HashMap<>();
		// 리턴 맵에 쿼리문 저장
		res.put("sql", sqlString);
		try {
			// sqlString에서 모두 기재하였기에 update에서는 args 미기재
			if (jdbcTemplate.update(sqlString) == 1) {
				// 쿼리문 정상 실행 시 저장된 내용 불러오기
				Map<String, Object> map = getMember(id);
				// 리턴 맵에 결과값 저장
				res.put("data", map.get("data"));
			}
		} catch (Exception e) {
			// 예외 발생 시 리턴 맵에 결과값 null로 저장
			res.put("data", null);
		}
		return res;
	}

	public Map<String, Object> updateMember(MemberVO vo) {
		// 실행할 쿼리문
		String sqlString = String.format("update member set name='%s', pass='%s' where id=%d", vo.getName(),
				vo.getPass(), vo.getId());
		// 리턴할 맵 새성
		Map<String, Object> res = new HashMap<>();
		// 리턴 맵에 쿼리문 저장
		res.put("sql", sqlString);
		try {
			// 쿼리문 정상 실행 시
			if (jdbcTemplate.update(sqlString) == 1) {
				// 저장된 내용 불러오기
				Map<String, Object> map = getMember(vo.getId());
				// 리턴 맵에 결과값 저장
				res.put("data", map.get("data"));
			}
		} catch (Exception e) {
			// 예외 발생 시 리턴 맵에 결과값 null로 저장
			res.put("data", null);
		}
		return res;
	}

	public Map<String, Object> removeMember(int id) {
		// 실행할 쿼리문
		String sqlString = String.format("delete from member where id = %d", id);
		// 리턴할 맵 생성
		Map<String, Object> res = new HashMap<>();
		// 리턴 맵에 쿼리문 저장
		res.put("sql", sqlString);
		try {
			// 삭제할 데이터 불러오기
			Map<String, Object> map = getMember(id);
			// 쿼리문 정상 실행 시
			if (jdbcTemplate.update(sqlString) == 1) {
				// 리턴 맵에 삭제 데이터 저장
				res.put("data", map.get("data"));
			}
		} catch (Exception e) {
			// 예외 발생 시 리턴 맵에 결과값 null로 저장
			res.put("data", null);
		}
		return res;
	}
}
