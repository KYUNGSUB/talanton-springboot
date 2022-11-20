package kr.talanton.sboot.stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class SiseInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;
	
	@Column(length=14, nullable=false)
	private String thistime;	// 시간
	
	@Column(length=10, nullable=false)
	private String cd;			// 종목 코드
	
	@Column(length=50, nullable=false)
	private String nm;			// 종목 명
	
	@Column(nullable=false)
	private int nv;			// 현재가
	
	@Column(nullable=false)
	private int cv;			// 등락
	
	@Column(nullable=false)
	private float cr;			// 등락율
	
	@Column(length=2)
	private String rf;			// 봉
	
	@Column(nullable=false)
	private int aq;			// 거래량
	
	@Column(length=10, nullable=false)
	private String ms;			// 상태
}