package kr.talanton.sboot.member.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import kr.talanton.sboot.common.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {
	@Id
	@Column(length=20, nullable=false)
	private String userid;
	@Column(length=64, nullable=false)
	private String password;
	@Column(length=30, nullable=false)
	private String name;
	@Column(length=30)
	private String nickname;
	@Column(length=30, nullable=false)
	private String email;
	@Column(length=15)
	private String phone;
	private String address;
	@Column(length=15)
	private String birthday;	// s/l.2000.12.12
	private boolean fromSocial;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole clubMemberRole){
        roleSet.add(clubMemberRole);
    }
}