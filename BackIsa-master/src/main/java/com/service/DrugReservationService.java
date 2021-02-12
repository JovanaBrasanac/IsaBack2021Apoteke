package com.service;

import com.model.Drug;
import com.model.DrugReservation;
import com.repository.DrugRepository;
import com.repository.DrugReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugReservationService {

	@Autowired
	private DrugReservationRepository drr;

	public List<DrugReservation> findAll() {
		return drr.findAll();
	}

	public DrugReservation findById(Long id) {
		return drr.findById(id).get();
	}

	public DrugReservation save(DrugReservation drugReservation) {
		return drr.save(drugReservation);
	}

	public void delete(DrugReservation dRes) {
		// TODO Auto-generated method stub
		drr.delete(dRes);
		
	}

}
