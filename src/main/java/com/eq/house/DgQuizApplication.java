package com.eq.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class DgQuizApplication {

	public static void main(String[] args) {
		new DgQuizApplication().go(args);
	}

	void go(String[] args) {
		SpringApplication.run(DgQuizApplication.class, args);
	}

}
