package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

//Kreiranje tabele u bazi sa nazivom klase
@Entity
public class PharmacyAdministrator {

	//Inkrementalno generisanje kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Kolone tabele ClinicAdministrator
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "email")
	private String email;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private int mobileNumber;
	@Column(nullable = false)
	private int jmbg;

	//CascadeType.ALL - menjanjem pregleda menjace se i recepti
	//LAZY - ucitavanje svih veza sa objektom pri ekspicitnom trazenju
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacy pharmacy;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>();

	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public PharmacyAdministrator() {
	}

	public PharmacyAdministrator(Long id, String username, String password, String email, Pharmacy pharmacy) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.pharmacy = pharmacy;
	}

}
