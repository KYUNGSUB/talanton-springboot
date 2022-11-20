package kr.talanton.sboot.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.talanton.sboot.stock.dto.PageRequestDTO;
import kr.talanton.sboot.stock.service.SiseinfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/sise")
@RequiredArgsConstructor
@Log4j2
public class SiseinfoController {
	private final SiseinfoService service;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list..." + pageRequestDTO);
		model.addAttribute("result", service.getListWithPage(pageRequestDTO));
	}
}