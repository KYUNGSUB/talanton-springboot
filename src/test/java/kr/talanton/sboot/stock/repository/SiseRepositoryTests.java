package kr.talanton.sboot.stock.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kr.talanton.sboot.stock.entity.SiseInfo;

@SpringBootTest
public class SiseRepositoryTests {
	@Autowired
	private SiseinfoRepository repository;
	
	@Test
	public void testSiseinfoList() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sid").descending());
		Page<SiseInfo> result = repository.findAll(pageable);
		if(result != null) {
			result.getContent().forEach(sise -> System.out.println(sise));
		}
	}
}