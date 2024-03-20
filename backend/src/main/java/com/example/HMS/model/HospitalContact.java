package com.example.HMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class HospitalContact {
	
	@Id
	@SequenceGenerator(name = "MY_ENTITY_SEQ", sequenceName = "MY_ENTITY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MY_ENTITY_SEQ" )
	private int id;
	private String contact_name;
	private String contact_number;
	private String email;
	
	public HospitalContact() {}

	public HospitalContact(int id, String contact_name, String contact_number, String email) {
		super();
		this.id = id;
		this.contact_name = contact_name;
		this.contact_number = contact_number;
		this.email = email;
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
