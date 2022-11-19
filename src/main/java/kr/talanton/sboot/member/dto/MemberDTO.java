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
	private String calendar;	// 양력(sonar), 음역(lunar)
	private String year;
	private String month;
	private String date;
	private String address;
	private boolean fromSocial;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}