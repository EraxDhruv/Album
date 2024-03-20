package com.example.HMS.Dto;

import java.util.List;

import com.example.HMS.model.Hospital;
import com.example.HMS.model.HospitalContact;

public class HospitalResponseDto {

	private Hospital hospital;
	private List<HospitalContact> hospitalContacts;
	
	public HospitalResponseDto() {}

	public HospitalResponseDto(Hospital hospital, List<HospitalContact> hospitalContacts) {
		super();
		this.hospital = hospital;
		this.hospitalContacts = hospitalContacts;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<HospitalContact> getHospitalContacts() {
		return hospitalContacts;
	}

	public void setHospitalContacts(List<HospitalContact> hospitalContacts) {
		this.hospitalContacts = hospitalContacts;
	}
	
	
	
}
