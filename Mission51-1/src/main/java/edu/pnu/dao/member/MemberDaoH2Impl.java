package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {

	private String sqlString;
	// template 생성
	private JdbcTemplate jdbcTemplate;

	// 이렇게도 사용 가능
	// Autowired이기 때문에 기본생성자 자동 생성됨
	@Autowired
	public MemberDaoH2Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// member 내의 모든 데이터 가져오기
	public List<MemberVO> getMembers() {
		sqlString = "select * from member";
		List<MemberVO> list = new ArrayList<>();
		try {
			// 여러 개의 값을 받을 수 있는 query() 사용
			// RowMapper를 오버라이딩 한 BeanPropertyRowMapper
			// table 형태로 있는 데이터를 MemberVO.class 타입에 맞춰 형변환하여 반환 요청
			list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getMembers Success");
		return list;
	}

	public MemberVO getMember(Integer id) {
		sqlString = "select * from member where id=?";
		MemberVO m = new MemberVO();
		try {
			// 하나의 객체만 받아오는 queryForObject 사용
			m = jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getMember Success");
		return m;
	}

	public MemberVO addMember(MemberVO vo) {
		sqlString = "insert into member(id,name,pass) values (?,?,?)";
		int id = 0;
		// 결과값 저장할 객체 선언
		MemberVO m = new MemberVO();
		try {
			// 기존 id의 최댓값+1 을 id로 가져오기
			id = jdbcTemplate.queryForObject("select max(id) from member", Integer.class) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// update()는 영향받은 행 수 반환
			if (jdbcTemplate.update(sqlString, id, vo.getName(), vo.getPass()) != 0) {
				// 실행된 결과값 가져오기
				m = getMember(id);
			} else {
				m = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("addMember Success");
		return m;
	}

	public MemberVO updateMember(MemberVO vo) {
		sqlString = "update member set name=?, pass=? where id=?";
		// 결과값 저장할 객체 선언
		MemberVO m = new MemberVO();
		try {
			if (jdbcTemplate.update(sqlString) != 0) {
				// 결과값 가져오기
				m = getMember(vo.getId());
			} else {
				m = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("updateMember Success");
		return m;
	}

	public MemberVO removeMember(Integer id) {
		sqlString = "delete from member where id=?";
		MemberVO m = new MemberVO();
		try {
			m = getMember(id);
			if (m != null && jdbcTemplate.update(sqlString) != 0) {
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("No search id");
		return null;
	}

	@Override
	public String getSqlString() {
		return sqlString;
	}
}
