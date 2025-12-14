package com.springapp.bookmarks_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(
	basePackages = "com.springapp.bookmarks_manager.Repository"
)
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class BookmarksManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarksManagerApplication.class, args);
	}

}
