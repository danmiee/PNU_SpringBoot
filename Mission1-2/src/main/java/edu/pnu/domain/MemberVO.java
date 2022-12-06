package edu.pnu.domain;

import java.util.Date;

public class MemberVO {

	private int id;
	private String name;
	private String pass;
	private Date regidate;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String name, String pass) {
		super();
		this.id = 0;
		this.name = name;
		this.pass = pass;
		this.regidate = new Date();
	}
	
	public MemberVO(int id, String name, String pass, Date regidate) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "memberVO [id=" + id + ", name=" + name + ", pass=" + pass + ", regidate=" + regidate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
