package com.tianyisoft.apidoc;

import com.tianyisoft.apidoc.aspect.SaveProject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class ApiDocApplication {
	@Bean
	public SaveProject saveProject() {
		return new SaveProject();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiDocApplication.class, args);
	}

}
