package com.example.HMS.controller;

import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HMS.Dto.JwtResponseDto;
import com.example.HMS.Dto.MessageDto;
import com.example.HMS.Dto.UserDto;
import com.example.HMS.model.User;
import com.example.HMS.service.CustomUserDetailService;
import com.example.HMS.service.UserService;
import com.example.HMS.util.JwtUtil;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
		Optional<User> checkUser = userService.getUser(userDto.getEmail());
		if (!checkUser.isEmpty()){
			MessageDto messageDto = new MessageDto();
			messageDto.setMessage("User already exists!");
			return new ResponseEntity<MessageDto>(messageDto, HttpStatus.BAD_REQUEST);
		}
		User user = userService.addUser(userDto);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<JwtResponseDto> login(@RequestBody UserDto userDto) throws Exception{
		System.out.println(userDto.getEmail());
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		
		final UserDetails userDetails = customUserDetailService.loadUserByUsername(userDto.getEmail());
		
		final String jwt = jwtUtil.generateToken(userDetails); 
		return new ResponseEntity<JwtResponseDto>(new JwtResponseDto(jwt), HttpStatus.OK);
	}
}
