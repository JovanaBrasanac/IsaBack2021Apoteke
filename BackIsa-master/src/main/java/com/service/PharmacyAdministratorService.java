package com.service;

import com.model.PharmacyAdministrator;
import com.repository.PharmacyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PharmacyAdministratorService {

	@Autowired
	private PharmacyAdministratorRepository car;

	public PharmacyAdministrator findByUsername(String username) {
		return car.findByUsername(username);
	}

	public List<PharmacyAdministrator> findByPharmacyId(Long id) {
		return car.findByPharmacyId(id);
	}
	
	public PharmacyAdministrator save(PharmacyAdministrator pharmacyAdministrator) {
		return car.save(pharmacyAdministrator);
	}
}
