package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Column(nullable = false)
	private boolean authenticated;


	@Column(nullable = false)
	private String description;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Singular("drug")
	private Set<Drug> drug;

	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Appointment appointment;

	@OneToOne(fetch = FetchType.LAZY)
	private Pharmacist pharmacist;
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	
	public Set<Drug> getDrug() {
		return drug;
	}

	public void setDrug(Set<Drug> drug) {
		this.drug = drug;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public Recipe() {
	}

	public Recipe(Long id, boolean authenticated, String description, Set<Drug> drug, Appointment appointment,
			Pharmacist pharmacist) {
		this.id = id;
		this.authenticated = authenticated;
		this.description = description;
		this.drug = drug;
		this.appointment = appointment;
		this.pharmacist = pharmacist;
	}

}
