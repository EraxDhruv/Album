package com.example.HMS.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.HMS.model.DicomPatient;
import com.example.HMS.repository.DicomPatientRepository;

@Service
public class DicomPatientService {

	@Value("${dicoms.folder}")
	private String FOLDER_PATH;
	@Autowired
	private DicomPatientRepository dicomPatientRepository;
	
	
	public String uploadImageToFileSystem(MultipartFile file,int hospitalId) throws IOException {
		Random random = new Random();
        String filePath=FOLDER_PATH+file.getOriginalFilename() + random.nextInt();

        DicomPatient dicom = new DicomPatient();
        dicom.setHospital_id(hospitalId);
        dicom.setFilepath(filePath);
        
        File newFile = new File(filePath);
        file.transferTo(newFile);
        
        Path dicomFile = newFile.toPath();
        DicomInputStream dis = new DicomInputStream(dicomFile.toFile());
        
        @SuppressWarnings("deprecation")
		Attributes fileAttributes = dis.readDataset(-1 , -1);
        
        LocalDate localDate = LocalDate.of(1970, Month.JANUARY, 01);
        
        String dicomPatientId = fileAttributes.getString(Tag.PatientID) == null ? "NONE" : fileAttributes.getString(Tag.PatientID);
        String patientName = fileAttributes.getString(Tag.PatientName) == null ? "NONE" : fileAttributes.getString(Tag.PatientName);
        Date date = fileAttributes.getDate(Tag.StudyDate) == null ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : fileAttributes.getDate(Tag.StudyDate);
        String modality = fileAttributes.getString(Tag.Modality) == null ? "NONE" : fileAttributes.getString(Tag.Modality) ;
        String age = fileAttributes.getString(Tag.PatientAge);
        Date dob = fileAttributes.getDate(Tag.PatientBirthDate);
        String sex = fileAttributes.getString(Tag.PatientSex);
        System.out.println(Tag.PatientAge+"date: " + Tag.PatientBirthDateAndTime);
        dicom.setPatient_id(dicomPatientId);
        dicom.setName(patientName);
        dicom.setUpload_date(date);
        dicom.setModality(modality);
        dicom.setAge(age);
        dicom.setDob(dob);
        dicom.setSex(sex);
        
        DicomPatient fileData=dicomPatientRepository.save(dicom);

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

	
    public byte[] downloadImage(int id) throws IOException{
       DicomPatient dicom = dicomPatientRepository.findById(id).orElse(null);
       String fileUrl = dicom.getFilepath();
        byte[] images = Files.readAllBytes(new File(fileUrl).toPath());
        System.out.println(images);
        return images;
    }
	
	public List<DicomPatient> getAllPatients(int hospital_id) {
		List<DicomPatient> dicomPatients = dicomPatientRepository.findByHospitalId(hospital_id);
		return dicomPatients;
	}
	
	public DicomPatient getDicomPatient (int id) {
		DicomPatient dicomPatient = dicomPatientRepository.findById(id).orElse(null);
		return dicomPatient;
	}
	
	public String deleteDicomPatient(int id) {
		dicomPatientRepository.deleteById(id);
		return "successfully deleted";
	}
}
