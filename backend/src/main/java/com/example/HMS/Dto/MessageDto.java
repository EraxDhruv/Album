package com.example.HMS.Dto;

public class MessageDto {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageDto(String message) {
		super();
		this.message = message;
	}
	
	public MessageDto() {}
}
