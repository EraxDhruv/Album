package com.example.HMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.HMS.Dto.HospitalContactDto;
import com.example.HMS.Dto.HospitalContactUpdateRequestDto;
import com.example.HMS.model.HospitalContact;
import com.example.HMS.repository.HospitalContactRepository;



@Service
public class HospitalContactService {
	
	@Autowired
	private HospitalContactRepository hospitalContactRepository;
	
	
	public List<HospitalContact> addContacts(List<HospitalContact> hospitalContactList) {
		List<HospitalContact> savedContacts = hospitalContactRepository.saveAll(hospitalContactList);
		return savedContacts;
	}
	
	public List<HospitalContact> getAllContacts(){
		return hospitalContactRepository.findAll();
	}
	
	public HospitalContact getContactById(int id){
		return hospitalContactRepository.findById(id).orElse(null);
	}
	
	public String deleteContact(int id) {
		Optional<HospitalContact> hospitalContact = hospitalContactRepository.findById(id);
		if (hospitalContact.isEmpty()) {
			return "contact id not found";
		}
		hospitalContactRepository.deleteById(id);
		return "Successfully deleted";
	}
	
	public List<HospitalContact> updateContacts(List<HospitalContact> hospitalContactsList){
		List<HospitalContact> updateContact = hospitalContactRepository.saveAll(hospitalContactsList);
		return updateContact;
	}

}
