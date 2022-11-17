package kr.talanton.sboot.member.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
	private String userid;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String phone;
	private String address;
	private String birthday;
	private boolean fromSocial;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}