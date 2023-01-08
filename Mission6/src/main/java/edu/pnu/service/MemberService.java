package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.MethodType;
import edu.pnu.domain.RequestLog;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.persistence.RequestLogRepository;
import jakarta.annotation.PostConstruct;

@Service
public class MemberService {

	// Repository 객체 생성
	private MemberRepository memberRepo;
	private RequestLogRepository logRepo;

	// 필드 사용 생성자
	@Autowired
	public MemberService(MemberRepository memberRepo, RequestLogRepository logRepo) {
		this.memberRepo = memberRepo;
		this.logRepo = logRepo;
	}

	@PostConstruct // 의존성 주입 후 초기화 설정
	public void init() {
		for (int i = 1; i <= 5; i++) {
			memberRepo.save(new Member("name" + i, "pass" + i));
		}
	}

	public List<Member> getMembers() {
		// findAll() : 기록되어있는 모든 member를 return
		List<Member> list = memberRepo.findAll();
		// member가 하나라도 있으면
		if (list != null)
			// true 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, "getMembers", true));
		else
			// 없으면 false 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, "getMembers", false));
		return list;
	}

	public Member getMember(Long id) {
		// Optional : Member가 null인 경우도 다룰 수 있도록 감싸는 Wrapper 클래스
		Optional<Member> option = memberRepo.findById(id);
		// option 값 확인
		Member m = option.get();
		// Optional 미사용 테스트
//		Member m = memberRepo.findById(id);

		// 해당 id를 가진 member가 있으면
		if (m != null)
			// member 정보 기재한 true 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%s)", m), true));
		else
			// 없으면 id 기재한 false 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%d)", id), false));
		return m;
	}

	public Member addMember(Member member) {
		// member 테이블에 내용 저장
		Member m = memberRepo.save(member);
		// 해당 member 정보 기재한 true 로그 기록
		logRepo.save(new RequestLog(MethodType.POST, String.format("addMemer(%s)", m), true));
		// id 자동 생성으로 false 기록 가능성 없음
		return m;
	}

	public Member updateMember(Member member) {
		// member 테이블에 내용 저장
		Member m = memberRepo.save(member);
		// 해당 member 정보 기재한 true 로그 기록
		logRepo.save(new RequestLog(MethodType.PUT, String.format("updateMemer(%s)", m), true));
		// primary key(id)를 기준으로 자동 저장하여 false 기록 가능성 없음
		// 존재하지 않는 id 입력 시 자동 생성? - 확인 필요
		return m;
	}

	public Member removeMember(Long id) {
		// Optional : Member가 null인 경우도 다룰 수 있도록 감싸는 Wrapper 클래스
		Optional<Member> option = memberRepo.findById(id);
		// option 값 확인
		Member m = option.get();
		// Optional 미사용 테스트
//		Member m = memberRepo.findById(id);

		// 해당 id를 가진 member가 있으면
		if (m != null) {
			memberRepo.deleteById(id);
			// member 정보 기재한 true 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%s)", m), true));
		}
		else {
			// 없으면 id 기재한 false 로그 기록
			logRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%d)", id), false));
		}
		return m;
	}

}
