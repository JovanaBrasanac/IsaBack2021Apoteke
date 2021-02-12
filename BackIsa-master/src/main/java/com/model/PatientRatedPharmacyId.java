package com.model;

import java.io.Serializable;

public class PatientRatedPharmacyId implements Serializable {

	private Long pharmacy;

	private Long patient;

	public Long getPatient() {
		return patient;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}

	public Long getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Long pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public PatientRatedPharmacyId() {
	}

	public PatientRatedPharmacyId(Long pharmacy, Long patient) {
		this.pharmacy = pharmacy;
		this.patient = patient;
	}
}
