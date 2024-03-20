package com.example.HMS.Dto;

public class DicomPatientResponseDto {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DicomPatientResponseDto(String message) {
		super();
		this.message = message;
	}
}
