package edu.pnu.domain;

import java.util.Date;

public class MemberVO {
    private int id;
    private String pass;
    private String name;
    private Date regidate;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getRegidate() {
        return regidate;
    }
    public void setRegidate(Date regidate) {
        this.regidate = regidate;
    }
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(int id, String pass, String name, Date regidate) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.regidate = regidate;
	}
	
	// memberVO끼리의 비교를 위해 equals 오버라이딩
    public boolean equals(MemberVO m) {
    	if(this.id == m.id) {
    		return true;
    	}
    	return false;
    }
}
