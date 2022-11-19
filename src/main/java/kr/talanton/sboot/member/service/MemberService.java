package kr.talanton.sboot.member.service;

import java.util.HashSet;

import kr.talanton.sboot.member.dto.MemberDTO;
import kr.talanton.sboot.member.entity.Member;
import kr.talanton.sboot.member.entity.MemberRole;

public interface MemberService {
	boolean idCheck(String userid);
	void join(MemberDTO member);
	
	default Member dtoToEntity(MemberDTO dto) {
		Member member = Member.builder()
				.userid(dto.getUserid())
				.password(dto.getPassword())
				.name(dto.getName())
				.nickname(dto.getNickname())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.birthday(dto.getCalendar()+"."+dto.getYear()+"."+dto.getMonth()+"."+dto.getDate())
				.fromSocial(false)
				.roleSet(new HashSet<MemberRole>())
				.build();
		member.addMemberRole(MemberRole.USER);
		return member;
	}
}