package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@Commit
class PasswordEncoderTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void testInsert() {
		Member m = new Member();
		m.setId("manager2");
		m.setPassword(encoder.encode("manager456"));
		m.setName("매니저2");
		m.setRole(Role.ROLE_MANAGER);
		m.setEnabled(true);
		memberRepo.save(m);
	}

}
