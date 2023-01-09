package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// 객체로 설정
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // 식별자 변수(primary key)로 매핑
	// GenerationType.IDENTITY : AUTO_INCREMENT 설정
	private String name;
	private String pass;
	private Date regidate;
	
	public Member() {
		// addMember 실행 시 regidate가 null로 저장되는 문제 해결 
		regidate = new Date();
	}
	
	// service에서 초기화할 때 간편하게 작성하기 위해 추가 생성	
	public Member(String name, String pass) {
		this.id = -1L;
		this.pass = pass;
		this.name = name;
		this.regidate = new Date();
	}

	public Member(Long id, String name, String pass, Date regidate) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", pass=" + pass + ", regidate=" + regidate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
}
