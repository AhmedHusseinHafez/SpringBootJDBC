package com.global.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class JdbcHrProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcHrProjectApplication.class, args);
	}

}
