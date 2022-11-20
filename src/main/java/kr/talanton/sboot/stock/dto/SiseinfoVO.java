package kr.talanton.sboot.stock.dto;

import java.util.List;

import lombok.Data;

@Data
public class SiseinfoVO {
	private int totCnt;
	private String ms;
	private List<SiseinfoDTO> itemList;
}