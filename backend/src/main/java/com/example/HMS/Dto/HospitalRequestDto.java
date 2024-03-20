package com.example.HMS.Dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.HMS.model.HospitalContact;


public class HospitalRequestDto {
	
	private String name;
	private String street_address;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	private List<HospitalContact> hospitalContacts;
	
	public HospitalRequestDto() {}
	

	public HospitalRequestDto(String name, String street_address, String city, String state, String country,
			String pincode, List<HospitalContact> hospitalContacts) {
		super();
		this.name = name;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.hospitalContacts = hospitalContacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public List<HospitalContact> getHospitalContacts() {
		return hospitalContacts;
	}

	public void setHospitalContacts(List<HospitalContact> hospitalContacts) {
		this.hospitalContacts = hospitalContacts;
	}	
	
}
