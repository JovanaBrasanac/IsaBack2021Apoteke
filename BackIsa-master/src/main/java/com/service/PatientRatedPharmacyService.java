package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.PatientRatedPharmacy;
import com.repository.PatientRatedPharmacyRepository;

import java.util.List;

@Service
public class PatientRatedPharmacyService {

	@Autowired
	private PatientRatedPharmacyRepository prcr;

	public PatientRatedPharmacy save(PatientRatedPharmacy prPharmacy) {
		return prcr.save(prPharmacy);
	}

	public List<PatientRatedPharmacy> findAll() {
		return prcr.findAll();
	}
}
