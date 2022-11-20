package kr.talanton.sboot.stock.dto;

import lombok.Data;

@Data
public class SiseResponseVO {
	private SiseinfoVO result;
	private String resultCode;
}