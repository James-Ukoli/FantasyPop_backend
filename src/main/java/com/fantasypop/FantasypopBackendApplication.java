package com.fantasypop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// APIs - Users(1), Posts(2), Comments(3), Follows(4), Notifications(5), Messages(6), NewsFeed(7), (Hashtag)
@SpringBootApplication
public class FantasypopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasypopBackendApplication.class, args);
	}

}
