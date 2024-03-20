package com.example.HMS.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.HMS.Dto.HospitalRequestDto;
import com.example.HMS.Dto.HospitalResponseDto;
import com.example.HMS.Dto.HospitalUpdateRequestDto;
import com.example.HMS.model.Hospital;
import com.example.HMS.service.HospitalService;


@RestController
@RequestMapping(value = "/api/hospitals")
@CrossOrigin(origins="*")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@PostMapping(value = "/")
	public ResponseEntity<Hospital> addHospital(@RequestBody HospitalRequestDto hospitalRequestDto) {
		Hospital hospital = hospitalService.addHospital(hospitalRequestDto);
		return new ResponseEntity<>(hospital, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<HospitalResponseDto>> getAllHospitals(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(value="name" ,required = false) String name, @RequestParam(value="city" ,required = false) String city, @RequestParam(value="code", required = false) String code){
		return new ResponseEntity<>( hospitalService.getAllHospitals(pageNo, pageSize, name, city, code),HttpStatus.OK);
	}
	
	@GetMapping(value = "/hospital")
	public ResponseEntity<HospitalResponseDto> getHospitalById(@RequestParam int id) {
		HospitalResponseDto hospital=  hospitalService.getHospitalById(id);
		return new ResponseEntity<>(hospital, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/")
	public ResponseEntity<HospitalResponseDto> updateHospital(@RequestBody HospitalUpdateRequestDto hospitalRequestDto, @RequestParam int id) {
		HospitalResponseDto hospitalResponse = hospitalService.getHospitalById(id);
		HospitalResponseDto updatedHospital= hospitalService.updateHospital(hospitalRequestDto, id);
//		if (updatedHospital != null) {
//			
//		}
		return new ResponseEntity<>(updatedHospital, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/")
	public ResponseEntity<String> deleteHospital(@RequestParam int id){
		HospitalResponseDto hospital = hospitalService.getHospitalById(id);
		if (hospital == null) {
			return new ResponseEntity<>("This hospital_Id doesn't exists",HttpStatus.NOT_FOUND);
		}
		String response = hospitalService.deleteHospital(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
