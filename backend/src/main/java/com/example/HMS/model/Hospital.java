package com.example.HMS.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.springframework.lang.NonNull;

@Entity
public class Hospital {

	@Id
	@SequenceGenerator(name = "MY_ENTITY_SEQ", sequenceName = "MY_ENTITY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MY_ENTITY_SEQ" )
	private int id;
	
	@NonNull
	@BatchSize(size = 3)
	private String name;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "String")
	private String code;
	@NonNull
	private String street_address;
	@NonNull
	private String city;
	@NonNull
	private String state;
	@NonNull
	private String country;
	@NonNull
	private String pincode;
	@NonNull
	private Integer first_contact_id;
	@NonNull
	private Integer second_contact_id;
	
	public Hospital() {}
	
	public Hospital(Integer id, String name, String code, String street_address, String city, String state,
			String country, String pincode, Integer first_contact_id, Integer second_contact_id) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.first_contact_id = first_contact_id;
		this.second_contact_id = second_contact_id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Integer getFirst_contact_id() {
		return first_contact_id;
	}
	public void setFirst_contact_id(Integer first_contact_id) {
		this.first_contact_id = first_contact_id;
	}
	public Integer getSecond_contact_id() {
		return second_contact_id;
	}
	public void setSecond_contact_id(Integer second_contact_id) {
		this.second_contact_id = second_contact_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
