package kr.talanton.sboot.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import kr.talanton.sboot.member.entity.MemberRole;
import kr.talanton.sboot.member.dto.AuthMemberDTO;
import kr.talanton.sboot.member.dto.MemberDTO;
import kr.talanton.sboot.member.entity.Member;
import kr.talanton.sboot.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {
	private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

	@Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("--------------------------------------");
        log.info("userRequest:" + userRequest);
        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        log.info("==============================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k +":" + v);
        });
        String email = null;
        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }
        log.info("EMAIL: " + email);
        Member member = saveSocialMember(email); //조금 뒤에 사용
        AuthMemberDTO clubAuthMember = new AuthMemberDTO(
                entityToDTO(member),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
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

	private Member saveSocialMember(String email){
        //기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
        Optional<Member> result = repository.findByEmail(email, true);
        if(result.isPresent()){
            return result.get();
        }
        //없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        Member member = Member.builder().email(email)
                .name(email)
                .password( passwordEncoder.encode("1111") )
                .nickname("똘똘이")
                .fromSocial(true)
                .build();
        member.addMemberRole(MemberRole.USER);
        repository.save(member);
        return member;
    }
}