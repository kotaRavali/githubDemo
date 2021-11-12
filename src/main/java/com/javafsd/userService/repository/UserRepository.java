package com.javafsd.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafsd.userService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<  User, Long >{
	
	//public User findById(Long userId);

	public User findByUserId(Long userId);

}
