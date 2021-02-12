package com.dto;

import com.model.Pharmacy;

public class PharmacyDTO {

	private Long id;
	private SystemAdministratorDTO systemAdministratorDTO;
	private String name;
	private String address;
	private int pricelist;
	private String description;
	private int profit;
	private int rating;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SystemAdministratorDTO getSystemAdministratorDTO() {
		return systemAdministratorDTO;
	}

	public void setSystemAdministratorDTO(SystemAdministratorDTO systemAdministratorDTO) {
		this.systemAdministratorDTO = systemAdministratorDTO;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPricelist() {
		return pricelist;
	}

	public void setPricelist(int pricelist) {
		this.pricelist = pricelist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}


	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PharmacyDTO() {
		super();
	}

	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getName(), pharmacy.getAddress(), pharmacy.getPricelist(), pharmacy.getDescription(), pharmacy.getProfit(),
				pharmacy.getRating());
	}

	public PharmacyDTO(String name, String address, int pricelist, String description, int profit, int rating, long id) {
		super();
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;
		this.id = id;
	}

	public PharmacyDTO(Long id, String name, String address, int pricelist, String description, int profit, int rating) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;
	}

	public PharmacyDTO(String name, String address, int pricelist, String description, int profit, int rating) {
		super();
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;

	}

	@Override
	public String toString() {
		return "ClinicDTO{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", pricelist="
				+ pricelist + ", description='" + description + '\'' + ", profit=" + profit + ", rating=" + rating
				+ '}';
	}
}
