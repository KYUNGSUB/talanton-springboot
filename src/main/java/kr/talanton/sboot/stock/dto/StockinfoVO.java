package kr.talanton.sboot.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockinfoVO {
	private String thistime;
	private String cd;
	private String nm;
	private String mt;
	private int nv;
	private int cv;
	private float cr;
	private String rf;
	private int pcv;
	private int mks;
	private int aq;
	private int aa;
	private String ms;
	private String tyn;
}