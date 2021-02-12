package com.repository;

import com.model.PatientRatedPharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRatedPharmacyRepository extends JpaRepository<PatientRatedPharmacy, Long> {

	List<PatientRatedPharmacy> findAll();
}
