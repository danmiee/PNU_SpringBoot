package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Table;

// 객체 생성 - table과 연계되는 클래스임을 알려줌
@Entity
// 연결할 table 설정 - persistence.xml에 입력하거나 아래 어노테이션 삽입
//@Table(name = "BOARD")	// 테이블명을 다르게 줄 수 있으나 명시적으로 구분되지 않기 때문에 권장하지 않음
public class Board {

	@Id				// Primary Key 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Auto Increment 설정 
	private Long seq;
	private String title;
	private String writer;
	private String content;
	// Date : 날짜 or 날짜+시간 설정 가능
	private Date createDate;
	private Long cnt;
	
	public Board() {
	}

	public Board(Long seq, String title, String writer, String content, Date createDate, Long cnt) {
		super();
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.createDate = createDate;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	
}
