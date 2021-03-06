package com.dto;

import com.model.SystemAdministrator;

public class SystemAdministratorDTO {

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String country;
	private int mobileNumber;
	private int jmbg;
	private int firstLog;

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

	public int getFirstLog() {
		return firstLog;
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

	public void setFirstLog(int firstLog) {
		this.firstLog = firstLog;
	}

	public SystemAdministratorDTO() {
		super();
	}

	public SystemAdministratorDTO(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public SystemAdministratorDTO(SystemAdministrator administrator) {
		super();
		this.id = administrator.getId();
		this.username = administrator.getUsername();
		this.password = administrator.getPassword();
	}
}
