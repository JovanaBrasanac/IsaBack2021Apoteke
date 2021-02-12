package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

	Pharmacy findByName(String name);
	
	List<Pharmacy> findAllByName(String name);

}
