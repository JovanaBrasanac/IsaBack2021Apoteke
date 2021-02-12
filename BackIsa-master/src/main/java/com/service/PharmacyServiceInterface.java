package com.service;

import com.dto.PharmacyDTO;
import com.model.Pharmacy;

import java.util.List;

public interface PharmacyServiceInterface {

	public List<Pharmacy> findAll();

	public Pharmacy findById(long id);

	public Pharmacy findByName(String name);
	
	public Pharmacy save(Pharmacy pharmacy);

	public void delete(Pharmacy pharmacy);

	public Pharmacy changeInfo(PharmacyDTO newPharmacy, String name);
}
