package com.example.HMS.Dto;

public class JwtResponseDto {
	String token;
	
	public JwtResponseDto() {}

	public JwtResponseDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
