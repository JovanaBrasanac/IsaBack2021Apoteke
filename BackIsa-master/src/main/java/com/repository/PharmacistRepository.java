package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Pharmacist;

import java.util.List;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

	List<Pharmacist> findByUsername(String username);

}
