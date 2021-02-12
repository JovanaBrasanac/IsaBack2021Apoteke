package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Kreiramo tabelu u bazi sa nazivom klase
@Entity
public class Pharmacy {

	//Inkrementalno dodeljivanje vrednosti kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)

	private Set<PriceList> priceList = new HashSet<PriceList>();

	//CascadeType.REFRESH - omogucava da aplikacija radi sa poslednjom verzijom entiteta iz baze
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

	private Set<RequestAppointment> requestAppointments = new HashSet<RequestAppointment>();
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

	private Set<Appointment> Appointments = new HashSet<Appointment>();


	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	
	private Set<PharmacyAdministrator> pharmacyAdministrator = new HashSet<PharmacyAdministrator>();

	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<Patient>();
	
	//EAGER - ucitavanje svih veza sa objektom odmah
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Doctor> doctors = new HashSet<Doctor>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
	private Set<PatientRatedPharmacy> patientRatedPharmacy = new HashSet<>();
	
	@JsonIgnore
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Drug> drugs = new HashSet<Drug>();
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<Promotion> promotions = new HashSet<Promotion>();


	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "pricelist", nullable = false)
	private int pricelist;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "profit", nullable = false)
	private int profit;
	@Column(name = "rating", nullable = false)
	private int rating;

	public int getRating() {
		return rating;
	}

	/*
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<Surgery>();
*/
	
	public Set<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<PriceList> getPriceList() {
		return priceList;
	}

	public void setPriceList(Set<PriceList> priceList) {
		this.priceList = priceList;
	}

	public Set<RequestAppointment> getRequestAppointments() {
		return requestAppointments;
	}

	public void setRequestAppointments(Set<RequestAppointment> requestAppointments) {
		this.requestAppointments = requestAppointments;
	}

	public Set<PharmacyAdministrator> getPharmacyAdministrator() {
		return pharmacyAdministrator;
	}

	public void setPharmacyAdministrator(Set<PharmacyAdministrator> pharmacyAdministrator) {
		this.pharmacyAdministrator = pharmacyAdministrator;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(Set<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public Set<PatientRatedPharmacy> getPatientRatedPharmacy() {
		return patientRatedPharmacy;
	}

	public void setPatientRatedPharmacy(Set<PatientRatedPharmacy> patientRatedPharmacy) {
		this.patientRatedPharmacy = patientRatedPharmacy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Set<Appointment> getAppointments() {
		return Appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		Appointments = appointments;
	}

	public Pharmacy() {
	}
}
