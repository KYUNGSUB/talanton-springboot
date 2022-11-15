package kr.talanton.sboot.member.service;

import org.springframework.stereotype.Service;

import kr.talanton.sboot.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	
}