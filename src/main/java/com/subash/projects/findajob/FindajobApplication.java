package com.subash.projects.findajob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.subash.projects.findajob.repository")
public class FindajobApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindajobApplication.class, args);
	}

}
