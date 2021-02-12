package com.dto;

import com.model.MedicalStaff;

public class PharmacistDTO extends MedicalStaffDTO {

	private PharmacistDTO pharmacistDTO;

	
	public PharmacistDTO getPharmacistDTO() {
		return pharmacistDTO;
	}

	public void setPharmacistDTO(PharmacistDTO pharmacistDTO) {
		this.pharmacistDTO = pharmacistDTO;
	}

	public PharmacistDTO() {
		super();
	}

	public PharmacistDTO(MedicalStaff medicalStaff) {
		super(medicalStaff);

	}

	public PharmacistDTO(Long id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);

	}
}
