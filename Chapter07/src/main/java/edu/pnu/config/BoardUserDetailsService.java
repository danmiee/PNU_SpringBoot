package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		} else {
			Member m = optional.get();
			
//			return new SecurityUser(m);
			
			// SecurityUser 미사용
//			return User.builder()
//					.username(m.getId())
//					.password(m.getPassword())
//					.roles(m.getRole().toString())
//					.build();
			
			// 암호화 적용 시 상단 코드로 리턴불가(사유불명)
			return new User(m.getId(), m.getPassword(),
					AuthorityUtils.createAuthorityList(m.getRole().toString()));
		
		}
	}

}
