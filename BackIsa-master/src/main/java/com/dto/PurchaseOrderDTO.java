package com.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.model.Drug;
import com.model.PharmacyAdministrator;
import com.model.PurchaseOffer;

public class PurchaseOrderDTO {
	
	private Long id;
	private Set<Drug> drugs = new HashSet<Drug>();
	private PharmacyAdministrator pharmacyAdmin;
	private Set<PurchaseOffer> purchaseOffers = new HashSet<PurchaseOffer>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Drug> getDrugs() {
		return drugs;
	}
	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}
	public PharmacyAdministrator getPharmacyAdmin() {
		return pharmacyAdmin;
	}
	public void setPharmacyAdmin(PharmacyAdministrator pharmacyAdmin) {
		this.pharmacyAdmin = pharmacyAdmin;
	}
	public Set<PurchaseOffer> getPurchaseOffers() {
		return purchaseOffers;
	}
	public void setPurchaseOffers(Set<PurchaseOffer> purchaseOffers) {
		this.purchaseOffers = purchaseOffers;
	}
	
	public PurchaseOrderDTO(Long id, Set<Drug> drugs, PharmacyAdministrator pharmacyAdmin,
			Set<PurchaseOffer> purchaseOffers) {
		super();
		this.id = id;
		this.drugs = drugs;
		this.pharmacyAdmin = pharmacyAdmin;
		this.purchaseOffers = purchaseOffers;
	}
	
	public PurchaseOrderDTO() {}
	
}
