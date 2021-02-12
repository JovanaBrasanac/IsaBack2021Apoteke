package com.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.PharmacyAdministrator;

public interface PharmacyAdministratorRepository extends JpaRepository<PharmacyAdministrator, Long> {

	List<PharmacyAdministrator> findByPharmacyId(Long id);
	
	PharmacyAdministrator findByUsername(String username);

	Page<PharmacyAdministrator> findAll(Pageable pageable);

}
