package kr.talanton.sboot.stock.service;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import kr.talanton.sboot.common.dto.PageResultDTO;
import kr.talanton.sboot.stock.dto.PageRequestDTO;
import kr.talanton.sboot.stock.dto.SiseinfoDTO;
import kr.talanton.sboot.stock.entity.QSiseInfo;
import kr.talanton.sboot.stock.entity.SiseInfo;
import kr.talanton.sboot.stock.repository.SiseinfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SiseinfoServiceImpl implements SiseinfoService {
	private final SiseinfoRepository repository;

	@Override
	public PageResultDTO<SiseinfoDTO, SiseInfo> getListWithPage(PageRequestDTO requestDTO) {
		log.info("getList... " + requestDTO);
		Pageable pageable = requestDTO.getPageable(Sort.by("sid").ascending());
		BooleanBuilder booleanBuilder = getSearch(requestDTO); //검색 조건 처리
		Page<SiseInfo> result = repository.findAll(booleanBuilder, pageable);
		Function<SiseInfo, SiseinfoDTO> fn = (entity -> entityToDTO(entity));
		return new PageResultDTO<>(result, fn);
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String date = requestDTO.getDate();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QSiseInfo qSiseInfo = QSiseInfo.siseInfo;
        BooleanExpression expression = qSiseInfo.sid.gt(0L); // sid > 0 조건만 생성
        booleanBuilder.and(expression);
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        String keyword;
        if(date == null || date.trim().length() == 0){ //검색 조건이 없는 경우
        	LocalDate now = LocalDate.now();
        	switch(now.getDayOfWeek()) {
        	case SUNDAY:
        		now = now.minusDays(2);
        		break;
        	case MONDAY:
        		now = now.minusDays(3);
        		break;
        	default:
        		now = now.minusDays(1);
        		break;
        	}
        	date = now.toString();
        }
        String[] dateArr = date.split("-");
        keyword = dateArr[0] + dateArr[1] + dateArr[2];
        //검색 조건을 작성하기
        conditionBuilder.or(qSiseInfo.thistime.contains(keyword));
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }
}