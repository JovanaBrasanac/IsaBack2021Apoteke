package com.dto;

import com.model.MedicalStaff;

public class DoctorDTO extends MedicalStaffDTO {

	private PharmacyDTO pharmacyDTO;

	private int review;

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public PharmacyDTO getPharmacyDTO() {
		return pharmacyDTO;
	}

	public void setPharmacyDTO(PharmacyDTO pharmacyDTO) {
		this.pharmacyDTO = pharmacyDTO;
	}

	public DoctorDTO() {
		super();

	}

	public DoctorDTO(Long id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);

	}

	public DoctorDTO(MedicalStaff medicalStaff) {
		super(medicalStaff);

	}

}
