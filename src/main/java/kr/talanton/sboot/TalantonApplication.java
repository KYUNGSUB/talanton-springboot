package kr.talanton.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TalantonApplication {
	public static void main(String[] args) {
		SpringApplication.run(TalantonApplication.class, args);
	}
}