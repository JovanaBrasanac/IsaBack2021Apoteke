package com.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.model.Pharmacy;

public class PromotionDTO
{

	private PharmacyDTO pharmacy;

	private String description;
	
	private String date;
	
	public PharmacyDTO getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(PharmacyDTO pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PromotionDTO(PharmacyDTO pharmacy, String description, String date) {
		this.pharmacy = pharmacy;
		this.description = description;
		this.date = date;
	}
	
	public PromotionDTO() {}




}
