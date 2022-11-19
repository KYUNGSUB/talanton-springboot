package kr.talanton.sboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Sise {
	private static final String BASIC_URL = "https://m.stock.naver.com/api/json/sise/siseListJson.nhn";
	private static final String MENU = "market_sum";
	private static final String SOSOK = "0";
	private static final int PAGE_SIZE = 20;	
	private static int pageNum = 1;

	public static void main(String[] args) {
		HttpsURLConnection conn = null;
		String resultJSON = "";
		URL url;

		try {
		    StringBuffer params = new StringBuffer();
		    params.append("menu=" + MENU);
		    params.append("&sosok=" + SOSOK);
		    params.append("&pageSize=" + PAGE_SIZE);
		    params.append("&page=" + pageNum);
				    
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
		    }
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println(resultJSON);
	}
}