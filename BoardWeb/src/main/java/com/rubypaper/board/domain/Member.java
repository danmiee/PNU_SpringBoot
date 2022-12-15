package com.rubypaper.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member { 
	@Id 
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String password, String name, Role role, boolean enabled, List<Board> boardList) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
		this.enabled = enabled;
		this.boardList = boardList;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + ", enabled="
				+ enabled + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
	
	
}
