package kr.talanton.sboot.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.talanton.sboot.stock.dto.SiseResponseVO;
import kr.talanton.sboot.stock.dto.SiseinfoVO;
import kr.talanton.sboot.stock.dto.StockinfoVO;
import kr.talanton.sboot.stock.entity.SiseInfo;
import kr.talanton.sboot.stock.repository.SiseinfoRepository;

@Component
public class Sise {
	private static final String BASIC_URL = "https://m.stock.naver.com/api/json/sise/siseListJson.nhn";
	private static final String MENU = "market_sum";
	private static final String SOSOK = "0";
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private SiseinfoRepository repository;
	
	public int processStockSiseInfo(int page) {
		int count = 0;
		List<StockinfoVO> itemList = getStockSiseInfo(page);
		if(itemList != null) {
			storeToDatabase(itemList);
			count = itemList.size();
		}
		return count;
	}

	public List<StockinfoVO> getStockSiseInfo(int page) {
		List<StockinfoVO> itemList = null;
		HttpsURLConnection conn = null;
		String resultJSON = "";
		URL url;

		try {
		    StringBuffer params = new StringBuffer();
		    params.append("menu=" + MENU);
		    params.append("&sosok=" + SOSOK);
		    params.append("&pageSize=" + PAGE_SIZE);
		    params.append("&page=" + page);
				    
		    url = new URL(BASIC_URL + "?" + params.toString());
				    
		    conn = (HttpsURLConnection) url.openConnection();
				        
		    if(conn != null) {              
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		        conn.setRequestMethod("GET");
		        conn.setDefaultUseCaches(false);
	              
		        conn.connect();
				            
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String input = null;
		        while ((input = br.readLine()) != null){
		            resultJSON += input;
		        }
		        br.close();
		        
		        ObjectMapper objectMapper = new ObjectMapper();
				SiseResponseVO dto = objectMapper.readValue(resultJSON, SiseResponseVO.class);
				if(dto.getResultCode().equals("success")) {
					SiseinfoVO result = dto.getResult();
					itemList = result.getItemList();
				}
		    }
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return itemList;
	}

	private void storeToDatabase(List<StockinfoVO> itemList) {
		for(StockinfoVO si : itemList) {
			SiseInfo sise = SiseInfo.builder()
					.thistime(si.getThistime())
					.cd(si.getCd())
					.nm(si.getNm())
					.nv(si.getNv())
					.cv(si.getCv())
					.cr(si.getCr())
					.rf(si.getRf())
					.aq(si.getAq())
					.ms(si.getMs())
					.build();
			repository.save(sise);
		}
	}

	public boolean isOpenNew() {
		boolean result = false;
		List<StockinfoVO> itemList = getStockSiseInfo(1);
		if(itemList != null) {
			StockinfoVO si = itemList.get(0);
			LocalDate now = LocalDate.now();
			String thistime = si.getThistime().substring(0, 8);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate yesterday = LocalDate.parse(thistime, formatter);
			Period p = Period.between(yesterday, now);
			if(p.getDays() == 1) {
				result = true;
			}
		}
		return result;
	}
}

/*
{"result":{"totCnt":1929,"ms":"CLOSE",
"itemList":[
{"thistime":"20221118161142","cd":"005930","nm":"삼성전자","mt":"0","nv":61800,"cv":400,"cr":0.65,"rf":"2","pcv":61400,"mks":3689326,"aq":11600647,"aa":718136,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161141","cd":"373220","nm":"LG에너지솔루션","mt":"0","nv":598000,"cv":1000,"cr":0.17,"rf":"2","pcv":597000,"mks":1399320,"aq":236148,"aa":142378,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161157","cd":"000660","nm":"SK하이닉스","mt":"0","nv":88400,"cv":700,"cr":0.8,"rf":"2","pcv":87700,"mks":643554,"aq":2854923,"aa":255793,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161110","cd":"207940","nm":"삼성바이오로직스","mt":"0","nv":882000,"cv":7000,"cr":0.8,"rf":"2","pcv":875000,"mks":627755,"aq":37485,"aa":33135,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161110","cd":"051910","nm":"LG화학","mt":"0","nv":704000,"cv":4000,"cr":0.57,"rf":"2","pcv":700000,"mks":496970,"aq":134700,"aa":95062,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161109","cd":"006400","nm":"삼성SDI","mt":"0","nv":701000,"cv":1000,"cr":0.14,"rf":"2","pcv":700000,"mks":482039,"aq":161514,"aa":114212,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161156","cd":"005935","nm":"삼성전자우","mt":"0","nv":57100,"cv":-100,"cr":-0.17,"rf":"5","pcv":57200,"mks":469868,"aq":559763,"aa":31940,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161157","cd":"005380","nm":"현대차","mt":"0","nv":169500,"cv":-500,"cr":-0.29,"rf":"5","pcv":170000,"mks":362168,"aq":531608,"aa":90919,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161125","cd":"035420","nm":"NAVER","mt":"0","nv":185500,"cv":-2500,"cr":-1.33,"rf":"5","pcv":188000,"mks":304311,"aq":748652,"aa":139973,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161142","cd":"000270","nm":"기아","mt":"0","nv":66000,"cv":-300,"cr":-0.45,"rf":"5","pcv":66300,"mks":267540,"aq":1232402,"aa":81634,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161157","cd":"035720","nm":"카카오","mt":"0","nv":57700,"cv":-1400,"cr":-2.37,"rf":"5","pcv":59100,"mks":256959,"aq":1689413,"aa":98350,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161156","cd":"068270","nm":"셀트리온","mt":"0","nv":178000,"cv":-1500,"cr":-0.84,"rf":"5","pcv":179500,"mks":250633,"aq":233063,"aa":41825,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161141","cd":"005490","nm":"POSCO홀딩스","mt":"0","nv":289500,"cv":5500,"cr":1.94,"rf":"2","pcv":284000,"mks":244834,"aq":399211,"aa":115960,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161141","cd":"028260","nm":"삼성물산","mt":"0","nv":121500,"cv":1500,"cr":1.25,"rf":"2","pcv":120000,"mks":227068,"aq":239872,"aa":29079,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161110","cd":"012330","nm":"현대모비스","mt":"0","nv":216000,"cv":5000,"cr":2.37,"rf":"2","pcv":211000,"mks":203656,"aq":161778,"aa":34743,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161141","cd":"105560","nm":"KB금융","mt":"0","nv":49000,"cv":450,"cr":0.93,"rf":"2","pcv":48550,"mks":200360,"aq":870446,"aa":42645,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161109","cd":"055550","nm":"신한지주","mt":"0","nv":36300,"cv":450,"cr":1.26,"rf":"2","pcv":35850,"mks":186195,"aq":950336,"aa":34443,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161124","cd":"003670","nm":"포스코케미칼","mt":"0","nv":224000,"cv":-500,"cr":-0.22,"rf":"5","pcv":224500,"mks":173518,"aq":613555,"aa":137748,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161110","cd":"096770","nm":"SK이노베이션","mt":"0","nv":172500,"cv":1500,"cr":0.88,"rf":"2","pcv":171000,"mks":159503,"aq":296527,"aa":51433,"ms":"CLOSE","tyn":"N"},
{"thistime":"20221118161158","cd":"034730","nm":"SK","mt":"0","nv":211000,"cv":-5500,"cr":-2.54,"rf":"5","pcv":216500,"mks":156455,"aq":161379,"aa":34385,"ms":"CLOSE","tyn":"N"}]},
"resultCode":"success"}
 */