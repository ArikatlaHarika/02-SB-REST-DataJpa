package com.javaexpress.service;

import com.javaexpress.exceptions.ResourceNotFoundException;
import com.javaexpress.models.Review;
import com.javaexpress.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	public void createUserInfo(User user) {
		log.info(" UserService :: createUserInfo {} ",user.getUsername());
		userRepository.save(user);
		log.info("user successfully saved in DB");
	
	}

    public User findByUserId(Long userId) {
	return 	userRepository.findById(userId).
			orElseThrow(()->new EntityNotFoundException("user not found"));
	}

	public User updateUser(Long userId, User inputUser) {
		return userRepository.findById(userId).map(user -> {
			user.setEmail(inputUser.getEmail());
			user.setUsername(inputUser.getUsername());
			user.setPassword(inputUser.getPassword());
			 return userRepository.save(user);
		}).orElseThrow(()->new EntityNotFoundException("user not found with id" +userId));

	}

	public void deleteUser(Long userId) {
		if(userRepository.existsById(userId)){
			userRepository.deleteById(userId);
		}else{
		throw new EntityNotFoundException("user not found with id" +userId);
		}
	}

    public void createReview(Long userId, Review review) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		review.setUser(user);
		// Perform additional validation if needed
		reviewRepository.save(review);
		log.info("review{} ", review.getComment());
	}


}

