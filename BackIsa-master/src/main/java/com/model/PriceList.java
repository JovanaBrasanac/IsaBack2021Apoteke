package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Tabela
@Entity
public class PriceList {

	//Genrisanje kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AppointmentType appointmentType;

	@Column(name = "price", nullable = false)
	private int price;
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PriceList() {
	}

	public PriceList(Pharmacy pharmacy, AppointmentType appointmentType, int price) {
		this.pharmacy = pharmacy;
		this.appointmentType = appointmentType;
		this.price = price;
	}

}
