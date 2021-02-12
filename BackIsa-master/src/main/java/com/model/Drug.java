package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "quantity", nullable = false)
	private int quantity;
	@Column(name = "price", nullable = false)
	private int price;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<DrugReservation> drugReservations = new HashSet<DrugReservation>();
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private PurchaseOrder purchaseOrder;

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Set<DrugReservation> getDrugReservations() {
		return drugReservations;
	}

	public void setDrugReservations(Set<DrugReservation> drugReservations) {
		this.drugReservations = drugReservations;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Drug() {
	}

	@Override
	public String toString() {
		return "Drug{" + "id=" + id + ", name='" + name + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
	}
}
