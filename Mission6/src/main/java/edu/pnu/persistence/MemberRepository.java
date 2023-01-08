package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

// JPA에서 추가한 기능을 사용할 수 있게하는 JpaRepository 상속
// 엔티티의 클래스 타입 : Member
// 식별자 변수(id) 타입 : Long
public interface MemberRepository extends JpaRepository<Member, Long> {

}
