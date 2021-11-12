package com.javafsd.userService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javafsd.userService.VO.Department;
import com.javafsd.userService.VO.ResponseTemplate;
import com.javafsd.userService.entity.User;
import com.javafsd.userService.exceptions.UserNotFoundException;
import com.javafsd.userService.repository.UserRepository;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String USERSERVICE = "userService";
	private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


	private Long fallbackId;
	 int attempts=1;

	public User saveUser(User user) {
		return userRepository.save(user);
	}


	@Retry(name = USERSERVICE, fallbackMethod = "fallback_getUserWithDepartment")
	public ResponseTemplate getUserWithDepartment(Long userId) {
		ResponseTemplate responseTemplate = new ResponseTemplate();
		LOGGER.info("Request Attempt "+ attempts++);
		fallbackId = userId;
		User userResponse = userRepository.findByUserId(userId);
		
		Department departmentResponse =
				restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + userResponse.getDeptId(),
						Department.class);
		
		if(userResponse == null || departmentResponse == null ) {
			throw new UserNotFoundException("User is not available in Database");
		}

		else {	

			responseTemplate.setUser(userResponse);
			responseTemplate.setDepartment(departmentResponse);
			
		return responseTemplate;
	}
	}


	public ResponseTemplate fallback_getUserWithDepartment(Exception exception) {
		LOGGER.info("Inside fallback method");
		ResponseTemplate responseTemplate = new ResponseTemplate();
		User userResponse = userRepository.findByUserId(fallbackId);
		if(userResponse == null) {
			throw new UserNotFoundException("User is not available in Database");
		}
		responseTemplate.setUser(userResponse);
		return responseTemplate;
	
		
	}
}