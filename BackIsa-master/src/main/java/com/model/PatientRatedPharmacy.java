package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;

//Tabele
@Entity
@IdClass(PatientRatedPharmacyId.class)
public class PatientRatedPharmacy implements Serializable {

	//Spajanje tabela na osnovu Id
	//Spajamo Kliniku i Pacijenta
	@Id
	@ManyToOne
	@JoinColumn
	private Pharmacy pharmacy;

	//Spajanje tabela na osnovu Id
	//Spajamo Kliniku i Pacijenta
	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private Patient patient;

	//Dodatna kolona
	@Column(nullable = true)
	private int ocena;

	public Patient getPatient() {
		return patient;
	}

	public int getOcena() {
		return ocena;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public PatientRatedPharmacy() {
	}

	public PatientRatedPharmacy(Pharmacy pharmacy, Patient patient, int ocena) {
		this.pharmacy = pharmacy;
		this.patient = patient;
		this.ocena = ocena;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
