package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

// JPA 기능 지원하는 Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
