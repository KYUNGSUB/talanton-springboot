package kr.talanton.sboot.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.talanton.sboot.member.dto.MemberDTO;
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
	
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String userid) {
		log.info("idCheck..." + userid);
		if(memberService.idCheck(userid)) {
			return "ok";
		}
		else {
			return "nok";
		}
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("join...");
	}
	
	@PostMapping("/join")
	public String join(MemberDTO member, RedirectAttributes rttr) {
		log.info("================================");
		log.info("register: " + member);
		memberService.join(member);
		rttr.addFlashAttribute("result", member.getUserid());
		return "redirect:/member/login";
	}
}