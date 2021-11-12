package com.javafsd.userService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafsd.userService.VO.ResponseTemplate;
import com.javafsd.userService.entity.User;
import com.javafsd.userService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User saveUser(@Valid @RequestBody  User user) {
		return userService.saveUser(user);

	}

	@GetMapping("/{id}")
	public ResponseTemplate getUserById(@PathVariable("id") Long userId) {
		return userService.getUserWithDepartment(userId);
	}
}

