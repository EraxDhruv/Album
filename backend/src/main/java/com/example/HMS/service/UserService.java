package com.example.HMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HMS.Dto.UserDto;
import com.example.HMS.model.User;
import com.example.HMS.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole("ADMIN,MANAGER");
		return userRepository.save(user);
	}
	
	public Optional<User> getUser(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user;
	}
}
