package kr.talanton.sboot.member.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.talanton.sboot.member.entity.Member;
import kr.talanton.sboot.member.entity.MemberRole;

@SpringBootTest
public class MemberRepositoryTests {
	@Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        //1 - 6까지는 USER만 지정
        //7 - 8까지는 USER,MANAGER
        //9 - 10까지는 USER,MANAGER,ADMIN

        IntStream.rangeClosed(1,10).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i+"@zerock.org")
                    .name("사용자"+i)
                    .nickname("똘이" + i)
                    .fromSocial(false)
                    .roleSet(new HashSet<MemberRole>())
                    .password(passwordEncoder.encode("1111"))
                    .build();
    
            //default role
            member.addMemberRole(MemberRole.USER);
            if(i > 6){
                member.addMemberRole(MemberRole.MANAGER);
            }
            if(i > 8){
                member.addMemberRole(MemberRole.ADMIN);
            }
            repository.save(member);
        });
    }
    
//    @Test
    public void testRead() {
        Optional<Member> result = repository.findByEmail("user9@zerock.org", false);
        Member member = result.get();
        System.out.println(member);
    }
}