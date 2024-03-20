package com.example.HMS.Dto;

public class HospitalContactUpdateRequestDto {
	private String contact_name;
	private String contact_number;
	private String email;
	private int id;
	
	public HospitalContactUpdateRequestDto() {}
	
	public HospitalContactUpdateRequestDto(String contact_name, String contact_number, String email, int id) {
		super();
		this.contact_name = contact_name;
		this.contact_number = contact_number;
		this.email = email;
		this.id = id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
