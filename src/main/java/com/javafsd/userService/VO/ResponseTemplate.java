package com.javafsd.userService.VO;

import com.javafsd.userService.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
	
	private User user;
	private Department department;
	
	
}

