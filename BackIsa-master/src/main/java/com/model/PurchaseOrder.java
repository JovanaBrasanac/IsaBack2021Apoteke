package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Drug> drugs = new HashSet<Drug>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private PharmacyAdministrator pharmacyAdmin;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<PurchaseOffer> purchaseOffers = new HashSet<PurchaseOffer>();
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public Set<Drug> getDrugs() {
		return drugs;
	}


	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}
	
	public Set<PurchaseOffer> getPurchaseOffers() {
		return purchaseOffers;
	}


	public void setPurchaseOffers(Set<PurchaseOffer> purchaseOffers) {
		this.purchaseOffers = purchaseOffers;
	}


	public PharmacyAdministrator getPharmacyAdmin() {
		return pharmacyAdmin;
	}


	public void setPharmacyAdmin(PharmacyAdministrator pharmacyAdmin) {
		this.pharmacyAdmin = pharmacyAdmin;
	}

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public PurchaseOrder() {
	}


}
