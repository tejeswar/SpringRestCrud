package com.pks.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

public class SpringRestClient {
	@Autowired
	private TopicService topicService;
	
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseURL = "http://localhost:8080/";
/*
	@GetMapping("/")
	 public String home (){
	  return "Spring REST by Prasanta on Java!!!";
	 }
	 
	 @GetMapping("/accounts")
	 public List<Account> all (){
	  return accountService.list();
	 }
	 
	 @PostMapping("/account")
	 public Account create (@RequestBody Account account){
	  return accountService.create(account);
	 }
	 
	 @GetMapping("/account/{accountId}")
	 public Account get (@PathVariable Long accountId){
	  return accountService.get(accountId);
	 }
	 
	 @PutMapping("/account/{accountId}")
	 public Account update (@RequestBody Account account, @PathVariable Long accountId){
	  return accountService.update(account, accountId);
	 }
	 
	 @DeleteMapping("/account/{accountId}")
	 public void delete (@PathVariable Long accountId){
	  accountService.delete(accountId);
	 }
*/
	public static void main(String[] args) {

		
		//Read Account details for a given accountId = 1 using GET method of RestTemplate
		  User userDetail = restTemplate.getForObject(baseURL+"user/2", User.class);
		  System.out.println("userDetail : " +userDetail); 
	}

}
