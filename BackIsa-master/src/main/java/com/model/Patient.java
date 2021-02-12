package com.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//Tabela
@Entity
public class Patient implements UserDetails {

	//Kljuc
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private MedicalRecord record;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	@JsonIgnore
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<PatientRatedDoctor> patientRatedDoctors = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<PatientRatedPharmacy> patientRatedPharmacy = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

	private Set<DrugReservation> drugReservations = new HashSet<DrugReservation>();
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Promotion> promotions = new HashSet<Promotion>();

	//Kolone
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String email;
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
	@Column(nullable = false)
	private boolean isEnabled;
	
	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Set<DrugReservation> getDrugReservations() {
		return drugReservations;
	}

	public void setDrugReservations(Set<DrugReservation> drugReservations) {
		this.drugReservations = drugReservations;
	}
	
	public MedicalRecord getRecord() {
		return record;
	}

	public void setRecord(MedicalRecord record) {
		this.record = record;
	}

	public void setEnabled(boolean enabled) {
		this.isEnabled = enabled;
	}

	public boolean isEnabled() {
		return this.isEnabled;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public Set<PatientRatedDoctor> getPatientRatedDoctors() {
		return patientRatedDoctors;
	}

	public Set<PatientRatedPharmacy> getPatientRatedPharmacy() {
		return patientRatedPharmacy;
	}

	public void setPatientRatedPharmacy(Set<PatientRatedPharmacy> patientRatedPharmacy) {
		this.patientRatedPharmacy = patientRatedPharmacy;
	}

	public void setPatientRatedDoctors(Set<PatientRatedDoctor> patientRatedDoctors) {
		this.patientRatedDoctors = patientRatedDoctors;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Patient() {
	}

	public Patient(String username, String password, String firstName, String lastName, String email, String address,
			String city, String country, int mobileNumber, int jmbg) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.jmbg = jmbg;
		this.isEnabled = true;
	}

}
