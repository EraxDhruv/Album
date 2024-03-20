package com.example.HMS.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.HMS.Dto.DicomPatientResponseDto;
import com.example.HMS.model.DicomPatient;
import com.example.HMS.service.DicomPatientService;

@RestController
@RequestMapping(value = "/api/patients/dicom")
@CrossOrigin(origins = "*")
public class DicomPatientController {
	
	@Autowired
	private DicomPatientService dicomPatientService;
	
	@PostMapping(value = "/upload/{hospitalId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<DicomPatientResponseDto> uploadDicom(@PathVariable int hospitalId, @RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = dicomPatientService.uploadImageToFileSystem(file,hospitalId);
		return new ResponseEntity<>(new DicomPatientResponseDto(uploadImage),HttpStatus.OK);
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable int patientId) throws IOException {
		byte[] imageData=dicomPatientService.downloadImage(patientId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	
	@GetMapping("/list")
	public ResponseEntity<List<DicomPatient>> getAllDicomPatients(@RequestParam int hospitalId){
		return new ResponseEntity<>(dicomPatientService.getAllPatients(hospitalId), HttpStatus.OK);
	}

}
