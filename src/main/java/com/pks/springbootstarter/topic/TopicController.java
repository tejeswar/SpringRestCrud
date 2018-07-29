package com.pks.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
//@SessionAttributes("employee")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	
	

	// output json converted object...bydefault RestController will convert the java
	// object to JSON format
	// Return of the response as below
	// [{"id":"Spring","name":"Spring Framework","description":"Spring Framework
	// Description"},{"id":"Java","name":"Core Java Framework","description":"Core
	// Java Framework Description"},{"id":"JavaScript","name":"JavaScript
	// Framework","description":"javaScript Framework Description"}]
	@RequestMapping(value = "/alltopics",method = RequestMethod.GET)
	public List<Topic> getAllTopics() {
		System.out.println("howgher");
		return topicService.getAllTopics();
	}
/*
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		EmployeeVO employeeVO = new EmployeeVO();
		model.addAttribute("employee", employeeVO);
		return topicService.getAllTopics();
		return "addEmployee";
	}
	*/

	

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(Model model) {
		return "addSuccess";
	}
	
	//tejeswar
	//-------------------Retrieve All Users--------------------------------------------------------
	
		@RequestMapping(value = "/user/", method = RequestMethod.GET)
		public ResponseEntity<List<User>> listAllUsers() {
			List<User> users = topicService.findAllUsers();
			if(users.isEmpty()){
				return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}


		//-------------------Retrieve Single User--------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> getUser(@PathVariable("id") long id) {
			System.out.println("Fetching User with id " + id);
			User user = topicService.findById(id);
			if (user == null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		
		
		//-------------------Create a User--------------------------------------------------------
		
		@RequestMapping(value = "/user/", method = RequestMethod.POST)
		public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
			System.out.println("Creating User " + user.getName());

			if (topicService.isUserExist(user)) {
				System.out.println("A User with name " + user.getName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			topicService.saveUser(user);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		
		//------------------- Update a User --------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
		public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
			System.out.println("Updating User " + id);
			
			User currentUser = topicService.findById(id);
			
			if (currentUser==null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			currentUser.setName(user.getName());
			currentUser.setAge(user.getAge());
			currentUser.setSalary(user.getSalary());
			
			topicService.updateUser(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		}

		//------------------- Delete a User --------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
			System.out.println("Fetching & Deleting User with id " + id);

			User user = topicService.findById(id);
			if (user == null) {
				System.out.println("Unable to delete. User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			topicService.deleteUserById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		
		//------------------- Delete All User --------------------------------------------------------
		
		@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
		public ResponseEntity<User> deleteAllUsers() {
			System.out.println("Deleting All Users");

			topicService.deleteAllUsers();
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}


}
