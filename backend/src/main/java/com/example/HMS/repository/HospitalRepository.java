package com.example.HMS.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.HMS.model.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> , PagingAndSortingRepository<Hospital, Integer>{
	public Optional<List<Hospital>> findAllByName(String name);

	
	@Query(value = "select * from hospital h where h.name Like concat('%', :name, '%') AND h.city Like concat('%', :city, '%') AND h.code Like concat('%', :code, '%')", nativeQuery = true)
	public Page<Hospital> findAllByFilter(Pageable paging,@Param("name") String name,@Param("city") String city,@Param("code") String code);
}

