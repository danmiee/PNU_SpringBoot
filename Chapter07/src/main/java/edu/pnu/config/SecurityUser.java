package edu.pnu.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import edu.pnu.domain.Member;

public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;
	
	public SecurityUser(Member m) {
		super(m.getId(), m.getPassword(),
				AuthorityUtils.createAuthorityList(m.getRole().toString()));
	}

}
