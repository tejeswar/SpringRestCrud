package com.pks.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHi() {
		
		return "***********Hello.......Welcome to Spring Boot World!!!!!...*********************";
	}

}
