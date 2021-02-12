package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "requestAppointments")
public class RequestAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Doctor doctor;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private MedicalRecord medicalRecord;

	@OneToOne(optional = true)
	private Recipe recipe;

	@Column(name = "patient", nullable = true)
	private String patient;
	@Column(name = "doctorUsername", nullable = true)
	private String doctorUsername;
	@Column(name = "date", nullable = true)
	private String date;
	@Column(name = "description", nullable = true)
	private String description;
	@Column(name = "type", nullable = true)
	private String type;
	@Column(name = "duration", nullable = true)
	private long duration;
	@Column(name = "price", nullable = true)
	private double price;


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RequestAppointment c = (RequestAppointment) o;
		if (c.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, c.id);
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + "]";
	}

	

	public RequestAppointment() {
	}

	public RequestAppointment(String patient, String date, String description, long duration) {
		this.patient = patient;
		this.date = date;
		this.description = description;
		this.duration = duration;
	}

	public RequestAppointment(String date, String description, long duration) {
		this.date = date;
		this.description = description;
		this.duration = duration;
	}
}
