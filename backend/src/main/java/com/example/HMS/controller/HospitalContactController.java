package com.example.HMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HMS.model.HospitalContact;
import com.example.HMS.service.HospitalContactService;

@RestController
@RequestMapping(value = "/api/hospitals")
public class HospitalContactController {
	
	@Autowired
	private HospitalContactService hospitalContactService;
	
	
	@GetMapping("/contacts/{contact_id}")
	public ResponseEntity<HospitalContact> getContact(@PathVariable int contact_id){
		return new ResponseEntity<>(hospitalContactService.getContactById(contact_id), HttpStatus.OK);
	}
	

}
