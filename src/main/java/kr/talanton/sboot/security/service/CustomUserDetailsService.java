package kr.talanton.sboot.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import kr.talanton.sboot.member.dto.AuthMemberDTO;
import kr.talanton.sboot.member.dto.MemberDTO;
import kr.talanton.sboot.member.entity.Member;
import kr.talanton.sboot.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("ClubUserDetailsService loadUserByUsername " + username);
        Optional<Member> result = memberRepository.findByEmail(username, false);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }

        Member member = result.get();
        log.info("-----------------------------");
        log.info(member);
        
        AuthMemberDTO clubAuthMember = new AuthMemberDTO(
                entityToDTO(member),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );
//        return null;
        return clubAuthMember;
	}

	private MemberDTO entityToDTO(Member member) {
		MemberDTO dto = MemberDTO.builder()
				.email(member.getEmail())
				.password(member.getPassword())
				.name(member.getName())
				.nickname(member.getNickname())
				.fromSocial(member.isFromSocial())
				.regDate(member.getRegDate())
				.modDate(member.getModDate())
				.build();
		return dto;
	}
}