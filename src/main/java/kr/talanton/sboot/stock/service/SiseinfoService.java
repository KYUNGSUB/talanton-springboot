package kr.talanton.sboot.stock.service;

import kr.talanton.sboot.common.dto.PageResultDTO;
import kr.talanton.sboot.stock.dto.PageRequestDTO;
import kr.talanton.sboot.stock.dto.SiseinfoDTO;
import kr.talanton.sboot.stock.entity.SiseInfo;

public interface SiseinfoService {
	public PageResultDTO<SiseinfoDTO, SiseInfo> getListWithPage(PageRequestDTO dto);
	
	default SiseinfoDTO entityToDTO(SiseInfo siseinfo) {
		SiseinfoDTO dto = SiseinfoDTO.builder()
				.sid(siseinfo.getSid())
				.thistime(siseinfo.getThistime())
				.cd(siseinfo.getCd())
				.nm(siseinfo.getNm())
				.nv(siseinfo.getNv())
				.cv(siseinfo.getCv())
				.cr(siseinfo.getCr())
				.rf(siseinfo.getRf())
				.aq(siseinfo.getAq())
				.ms(siseinfo.getMs())
				.build();
		return dto;
	}
}