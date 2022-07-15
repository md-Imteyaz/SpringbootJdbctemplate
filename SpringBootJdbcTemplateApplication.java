package com.JdbcTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
//@ImportResource({"classpath*:queries.xml."})
@SpringBootApplication
public class SpringBootJdbcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcTemplateApplication.class, args);
	}

}
