package com.example.HMS.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class DicomPatient {
	@Id
	@SequenceGenerator(name = "MY_ENTITY_SEQ", sequenceName = "MY_ENTITY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MY_ENTITY_SEQ" )
	private int id;
	private String name;
	private String sex;
	private String age;
	private String modality;
	private Date dob;
	private Date upload_date;//study date
	private String filepath;
	private String patient_id;
	private int hospital_id;
	
	public DicomPatient() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public DicomPatient(int id, String name, String sex, String age, String modality, Date dob, Date upload_date,
			String filepath, String patient_id, int hospital_id) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.modality = modality;
		this.dob = dob;
		this.upload_date = upload_date;
		this.filepath = filepath;
		this.patient_id = patient_id;
		this.hospital_id = hospital_id;
	}
	
	
	
}
