package kr.talanton.sboot.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiseinfoDTO {
	private Long sid;
	private String thistime;
	private String cd;
	private String nm;
	private int nv;
	private int cv;
	private float cr;
	private String rf;
	private int aq;
	private String ms;
}