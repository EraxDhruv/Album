package com.example.HMS.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.HMS.Dto.HospitalContactDto;
import com.example.HMS.Dto.HospitalRequestDto;
import com.example.HMS.Dto.HospitalResponseDto;
import com.example.HMS.Dto.HospitalUpdateRequestDto;
import com.example.HMS.model.Hospital;
import com.example.HMS.model.HospitalContact;
import com.example.HMS.repository.HospitalRepository;


@Service
public class HospitalService {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private HospitalContactService hospitalContactService;
	
	public Hospital addHospital(HospitalRequestDto hospitalDto) {
		
		List<HospitalContact> contactResponse = hospitalContactService.addContacts(hospitalDto.getHospitalContacts());
		Random random = new Random();
		Hospital hospital = new Hospital();
		String[] codeName = hospitalDto.getName().split(" ");
		hospital.setCode(codeName[0]+random.nextInt());
		hospital.setCity(hospitalDto.getCity());
		hospital.setState(hospitalDto.getState());
		hospital.setCountry(hospitalDto.getCountry());
		hospital.setPincode(hospitalDto.getPincode());
		String hospitalName = hospitalDto.getName().substring(0, 1).toUpperCase() + hospitalDto.getName().substring(1);
		hospital.setName(StringUtils.capitalize(hospitalDto.getName()));
		hospital.setStreet_address(hospitalDto.getStreet_address());
		hospital.setFirst_contact_id(contactResponse.get(0).getId());
		hospital.setSecond_contact_id(contactResponse.get(1).getId());
		return hospitalRepository.save(hospital);
	}
	
	public List<HospitalResponseDto> getAllHospitals(int pageNo, int pageSize, String name, String city, String code){
		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Hospital> results = hospitalRepository.findAllByFilter(paging, name, city, code); 
		List<HospitalResponseDto> responseHospital = new ArrayList<>();
		for (Hospital hospital : results.toList()) {
			HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
			List<HospitalContact> contacts = new ArrayList<>();
			contacts.add(hospitalContactService.getContactById(hospital.getFirst_contact_id()));
			contacts.add(hospitalContactService.getContactById(hospital.getSecond_contact_id()));
			hospitalResponseDto.setHospital(hospital);
			hospitalResponseDto.setHospitalContacts(contacts);
			responseHospital.add(hospitalResponseDto);
		}
		return responseHospital;
	}
	
	public HospitalResponseDto getHospitalById(int Id) {
		Hospital hospitalDetails = hospitalRepository.findById(Id).orElse(null);
		HospitalContact first_contact_id = hospitalContactService.getContactById(hospitalDetails.getFirst_contact_id());
		HospitalContact second_contact_id = hospitalContactService.getContactById(hospitalDetails.getSecond_contact_id());
		
		List<HospitalContact> contactsList = new ArrayList<>();
		contactsList.add(first_contact_id);
		contactsList.add(second_contact_id);
		
		HospitalResponseDto hospitalResponse = new HospitalResponseDto();
		hospitalResponse.setHospitalContacts(contactsList);
		hospitalResponse.setHospital(hospitalDetails);
		
		return hospitalResponse;
	}
	
	
	public HospitalResponseDto updateHospital(HospitalUpdateRequestDto hospitalDto, int hospitalId) {
		
		List<HospitalContact> contactResponse = hospitalContactService.updateContacts(hospitalDto.getHospitalContacts());
		Hospital hospitalDetails = hospitalRepository.findById(hospitalId).orElse(null);
		
		Hospital hospital = new Hospital();
		hospital.setId(hospitalId);
		String[] codeName = hospitalDto.getName().split(" ");
		hospital.setCode(hospitalDetails.getCode());
		hospital.setCity(hospitalDto.getCity());
		hospital.setState(hospitalDto.getState());
		hospital.setCountry(hospitalDto.getCountry());
		hospital.setPincode(hospitalDto.getPincode());
		hospital.setName(hospitalDto.getName());
		hospital.setStreet_address(hospitalDto.getStreet_address());
		hospital.setFirst_contact_id(contactResponse.get(0).getId());
		hospital.setSecond_contact_id(contactResponse.get(1).getId());
		
		hospitalRepository.save(hospital);
		
		HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
		hospitalResponseDto.setHospital(hospital);
		hospitalResponseDto.setHospitalContacts(contactResponse);
		
		return hospitalResponseDto;
	}
	
	public String deleteHospital(int hospitalId) {
		Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
		if(hospital.isEmpty()) {
			return "hospital not found";
		}
		int first_contact_id = hospital.get().getFirst_contact_id();
		int second_contact_id = hospital.get().getSecond_contact_id();
		hospitalRepository.deleteById(hospitalId);
		hospitalContactService.deleteContact(first_contact_id);
		hospitalContactService.deleteContact(second_contact_id);
		return "Successfully Deleted";
	}
}
