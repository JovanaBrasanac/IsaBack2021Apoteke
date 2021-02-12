package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PurchaseOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Drug> drugs = new HashSet<Drug>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private PurchaseOrder purchaseOrder;
	

	public Set<Drug> getDrugs() {
		return drugs;
	}


	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}


	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public PurchaseOffer() {
	}


}
