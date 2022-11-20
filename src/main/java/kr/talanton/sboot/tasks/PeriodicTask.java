package kr.talanton.sboot.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class PeriodicTask {
	@Autowired
	private Sise sise;
	
//	@Scheduled(cron = "0 0 2 * * *")
	@Scheduled(cron = "0 0 2 * * *")
	public void getStockSiseInfo() throws Exception {
		log.info("Stock Sise Information Get...");

		if(!sise.isOpenNew()) {
			return;
		}
		
		for(int i = 1;;i++) {	// 20개씩 가져오기
			if(sise.processStockSiseInfo(i) < 20) {
				break;
			}
			Thread.sleep(2000);
		}
	}
}