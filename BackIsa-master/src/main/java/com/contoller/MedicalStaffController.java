package com.contoller;

import com.dto.MedicalStaffDTO;
import com.model.Pharmacy;
import com.model.PharmacyAdministrator;
import com.repository.AppointmentTypeRepository;
import com.model.AppointmentType;
import com.model.Doctor;
import com.model.MedicalStaff;
import com.service.AppointmentTypeService;
import com.service.DoctorService;
import com.service.MedicalStaffService;
import com.service.PharmacyAdministratorService;
import com.service.PharmacyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalStaffController {

	@Autowired
	MedicalStaffService mss;
	@Autowired
	DoctorService ds;
	@Autowired
	PharmacyService cs;
	@Autowired
	PharmacyAdministratorService cas;
	@Autowired
	AppointmentTypeService ats;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/osoblje/{username}", method = RequestMethod.GET)
	public MedicalStaff getMedStaff(@PathVariable String username) {
		return mss.findByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/doctors", method = RequestMethod.GET)
	public List<MedicalStaff> getDoctors() {
		return mss.findByRole("doctor");
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/doctors/{usernameAdmin}", method = RequestMethod.GET)
	public List<Doctor> getDoctors(@PathVariable String usernameAdmin) {

		PharmacyAdministrator admin = cas.findByUsername(usernameAdmin);
		List<Doctor> doctors = new ArrayList<>();
		List<MedicalStaff> medicalStaffs = mss.findByRole("doctor");
		
		for (int i = 0; i < medicalStaffs.size(); i++) {
			Doctor doc = ((Doctor) medicalStaffs.get(i));
			if (doc.getPharmacy().getId().equals(admin.getPharmacy().getId()) && doc.getAppointmentType().getId() == 1) {
				doctors.add(doc);
			}
		}
		return doctors;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/dermatologists/{usernameAdmin}", method = RequestMethod.GET)
	public List<Doctor> getDoctorsDerm(@PathVariable String usernameAdmin) {

		PharmacyAdministrator admin = cas.findByUsername(usernameAdmin);
		List<Doctor> doctors = new ArrayList<>();
		List<MedicalStaff> medicalStaffs = mss.findByRole("doctor");
		
		for (int i = 0; i < medicalStaffs.size(); i++) {
			Doctor doc = ((Doctor) medicalStaffs.get(i));
			if (doc.getPharmacy().getId().equals(admin.getPharmacy().getId()) && doc.getAppointmentType().getId() == 2) {
				doctors.add(doc);
			}
		}
		return doctors;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/delete-doc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MedicalStaffDTO> deleteDoc(@RequestBody MedicalStaffDTO doctor) {
		Doctor hr = (Doctor) mss.findByUsername(doctor.getUsername());
		Pharmacy clinic = cs.findById(hr.getPharmacy().getId());
		clinic.getDoctors().remove(hr);
		cs.save(clinic);
		mss.delete(hr);
		MedicalStaffDTO medStaff = new MedicalStaffDTO();

		return new ResponseEntity<>(medStaff, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/add-doctor/{username}", method = RequestMethod.POST)
	public void addDoc(@RequestBody MedicalStaffDTO staff, @PathVariable String username) {
		Doctor d = new Doctor();
		d.setFirstName(staff.getFirstName());
		d.setAddress(staff.getAddress());
		d.setMobileNumber(staff.getMobileNumber());
		d.setKrajRadnogVremena(staff.getKrajRadnogVremena());
		d.setLastName(staff.getLastName());
		d.setEmail(staff.getEmail());
		d.setCountry(staff.getCountry());
		d.setCity(staff.getCity());
		d.setPassword(staff.getPassword());
		d.setJmbg(staff.getJmbg());
		d.setRole("doctor");
		d.setUsername(staff.getUsername());
		d.setPocetakRadnogVremena(staff.getPocetakRadnogVremena());
		
		AppointmentType appt = ats.findById(Long.valueOf(1));
		d.setAppointmentType(appt);
		mss.save(d);

		PharmacyAdministrator clinicAdministrator = cas.findByUsername(username);
		Pharmacy clinic = cs.findById(clinicAdministrator.getPharmacy().getId());
		if (clinic != null) {
			clinic.getDoctors().add(d);
			d.setPharmacy(clinic);
			mss.save(d);
			cs.save(clinic);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/add-doctorDerm/{username}", method = RequestMethod.POST)
	public void addDocDerm(@RequestBody MedicalStaffDTO staff, @PathVariable String username) {
		Doctor d = new Doctor();
		d.setFirstName(staff.getFirstName());
		d.setAddress(staff.getAddress());
		d.setMobileNumber(staff.getMobileNumber());
		d.setKrajRadnogVremena("18:00");
		d.setLastName(staff.getLastName());
		d.setEmail(staff.getEmail());
		d.setCountry(staff.getCountry());
		d.setCity(staff.getCity());
		d.setPassword(staff.getPassword());
		d.setJmbg(staff.getJmbg());
		d.setRole("doctor");
		d.setUsername(staff.getUsername());
		d.setPocetakRadnogVremena("10:00");
		
		AppointmentType appt = ats.findById(Long.valueOf(2));
		d.setAppointmentType(appt);
		mss.save(d);

		PharmacyAdministrator clinicAdministrator = cas.findByUsername(username);
		Pharmacy clinic = cs.findById(clinicAdministrator.getPharmacy().getId());
		if (clinic != null) {
			clinic.getDoctors().add(d);
			d.setPharmacy(clinic);
			mss.save(d);
			cs.save(clinic);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/osobljePromjena", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<MedicalStaff> changeInfo(@RequestBody MedicalStaffDTO mdNovi) {
		MedicalStaff md = mss.findByUsername(mdNovi.getUsername());
		if (md != null) {
			md.setPassword(mdNovi.getPassword());
			md.setFirstName(mdNovi.getFirstName());
			md.setLastName(mdNovi.getLastName());
			md.setCity(mdNovi.getCity());
			md.setCountry(mdNovi.getCountry());
			md.setEmail(mdNovi.getEmail());
			md.setMobileNumber(mdNovi.getMobileNumber());
			mss.save(md);
		} else {
		}
		return new ResponseEntity<>(md, HttpStatus.OK);
	}
}
