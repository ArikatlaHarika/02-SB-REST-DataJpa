package com.javaexpress.controller;

import com.javaexpress.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaexpress.models.User;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	//http://localhost:8080/api/v1/users
	@PostMapping("/{userId}/reviews")
	public ResponseEntity<String> createReview(@PathVariable Long userId, @RequestBody Review review) {
		userService.createReview(userId, review);
		return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
	}


	
	//RequestBody conversion it will take care to get json format
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //201
	public void createUser(@RequestBody User user) {
		log.info(" UserController :: createUser {} ",user.getUsername());
		userService.createUserInfo(user);
		
	}
	
	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info(" UserController :: fetchUser {} ");
	return userService.findByUserId(userId);
		
		
		
	}
	@PutMapping("{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId ) {
		return userService.updateUser(userId,user);
    }
	@DeleteMapping("{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void deleteUser(@PathVariable Long userId){

		userService.deleteUser(userId);
    }
}
