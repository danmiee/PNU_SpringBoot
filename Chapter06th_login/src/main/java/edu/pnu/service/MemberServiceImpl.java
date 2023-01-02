package edu.pnu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		// Optional.isPresent : 객체가 값을 가지고 있다면 true, 없다면 false 리턴
		if(findMember.isPresent())
			// 매개변수로 들어온 member의 id가 세션에 저장되어 있으면 그 값을 리턴
			return findMember.get();
		else return null;
	}
}
