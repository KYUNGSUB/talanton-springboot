package kr.talanton.sboot.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.talanton.sboot.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		log.info("login...");
	}
}