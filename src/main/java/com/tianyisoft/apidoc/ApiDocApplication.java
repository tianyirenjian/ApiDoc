package com.tianyisoft.apidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class ApiDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDocApplication.class, args);
	}

}
