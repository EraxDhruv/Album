package com.example.HMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HMS.Dto.HospitalContactUpdateRequestDto;
import com.example.HMS.model.HospitalContact;

@Repository
public interface HospitalContactRepository extends JpaRepository<HospitalContact, Integer>{}
