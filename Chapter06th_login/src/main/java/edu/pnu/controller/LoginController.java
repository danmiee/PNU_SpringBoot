package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberServiceImpl;

@SessionAttributes("member")
@Controller
public class LoginController {

	@Autowired
	private MemberServiceImpl ms;

	@GetMapping("/login")	// 로그인 하지않고 주소창에 입력해서 접근
	public void loginView() {
	}

	@PostMapping("/login")	// login.html의 form태그를 통해 호출되는 페이지
	public String login(Member member, Model model) {
		Member findMember = ms.getMember(member);

		if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
			// 로그인 가능 > model에 저장 > 게시판 열기
			model.addAttribute("member", findMember);
			return "forward:getBoardList3";
		} else {
			// 로그인 불가 > 로그인페이지 노출
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")	// 로그아웃하면 세션에 저장했던 내용 비우고 홈으로 돌아가기 
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
		
	}
	
}
