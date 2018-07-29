package com.pks.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages= {"com.pks.springbootstarter","com.pks.springboot.services","com.pks.springbootstarter.hello","com.pks.springbootstarter.topic","com.websystique.springmvc"})

public class CourseApiApp {

	public static void main(String[] args) {
		SpringApplication.run(CourseApiApp.class, args);
		System.out.println("Hello World");
		

	}

}
