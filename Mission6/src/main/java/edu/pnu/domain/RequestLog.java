package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity	// entity 지정
public class RequestLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Increment
	private Long id; // 식별자 변수 설정
	@Enumerated(EnumType.STRING)
	private MethodType method; // enum 활용
	private String sqlstring;
	@Temporal(TemporalType.TIMESTAMP) // db 타입에 맞게 매핑
	private Date regidate;
	private boolean success;

	// 기본 생성자
	public RequestLog() {

	}

	// 수동 입력해야하는 요소를 parameter로 갖는 생성자
	public RequestLog(MethodType method, String sqlstring, boolean success) {
		this.id = -1L;
		this.method = method;
		this.sqlstring = sqlstring;
		this.regidate = new Date();
		this.success = success;
	}

	// 모든 요소를 parameter로 갖는 생성자
	public RequestLog(Long id, MethodType method, String sqlstring, Date regidate, boolean success) {
		this.id = id;
		this.method = method;
		this.sqlstring = sqlstring;
		this.regidate = regidate;
		this.success = success;
	}

	@Override
	public String toString() {
		return "RequestLog [id=" + id + ", method=" + method + ", sqlstring=" + sqlstring + ", regidate=" + regidate
				+ ", success=" + success + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MethodType getMethod() {
		return method;
	}

	public void setMethod(MethodType method) {
		this.method = method;
	}

	public String getSqlstring() {
		return sqlstring;
	}

	public void setSqlstring(String sqlstring) {
		this.sqlstring = sqlstring;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
