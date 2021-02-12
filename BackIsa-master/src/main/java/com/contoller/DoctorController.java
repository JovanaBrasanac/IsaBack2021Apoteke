package com.contoller;

import com.dto.PatientRatedClinicDTO;
import com.dto.PatientRatedDoctorDTO;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DoctorController {

	@Autowired
	private AppointmentService as;
	@Autowired
	private PatientService ps;
	@Autowired
	private PatientRatedDoctorService prds;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private PatientRatedPharmacyService prcs;
	@Autowired
	private PharmacyService cs;

	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/getDoctorsForRate/{username}", method = RequestMethod.GET)
	public List<Doctor> getDoctorsForRate(@PathVariable String username) {

		List<Appointment> pregledi = as.findAll();
		List<Doctor> doctors = new ArrayList<>();
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					doctors.add(app.getDoctor());
				}
			}
		}
		return doctors;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getRate/{username}", method = RequestMethod.GET)
	public List<PatientRatedDoctor> getRate(@PathVariable("username") String username) {

		List<Appointment> pregledi = as.findAll();
		List<Doctor> doctors = new ArrayList<>();
		List<Doctor> doctorsFromPrd = new ArrayList<>();
		List<PatientRatedDoctor> ret = new ArrayList<>();
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					doctors.add(app.getDoctor());
				}
			}
		}
		Patient patient = ps.findByUsername(username);
		List<PatientRatedDoctor> prd = prds.findAll();
		for (PatientRatedDoctor p1 : prd) {
			if (p1.getPatient() != null) {
				if (p1.getPatient().getUsername().equals(username)) {
					doctorsFromPrd.add(p1.getDoctor());
				}
			}
		}
		for (Doctor doc : doctors) {
			if (!doctorsFromPrd.contains(doc)) {
				PatientRatedDoctor noviPrd = new PatientRatedDoctor();
				noviPrd.setOcena(0);
				noviPrd.setPatient(patient);
				noviPrd.setDoctor(doc);
				prds.save(noviPrd);
			}
		}
		List<PatientRatedDoctor> prd1 = prds.findAll();
		for (PatientRatedDoctor p : prd1) {
			if (p.getPatient().getUsername().equals(username)) {
				ret.add(p);
			}
		}
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getPharmacysForRate/{username}", method = RequestMethod.GET)
	public List<PatientRatedPharmacy> getClinicsForRate(@PathVariable("username") String username) {

		List<Appointment> pregledi = as.findAll();
		List<Pharmacy> clinics = new ArrayList<>();
		List<Pharmacy> clinicsFromPrclinic = new ArrayList<>();
		List<PatientRatedPharmacy> ret = new ArrayList<>();
		Patient patient = ps.findByUsername(username);
		for (Appointment app : pregledi) {
			if (app.getPatient() != null) {
				if (app.getPatient().equals(username) && app.isFinished()) {
					clinics.add(app.getDoctor().getPharmacy());
				}
			}
		}

		List<PatientRatedPharmacy> prclinic = prcs.findAll();
		for (PatientRatedPharmacy prclin : prclinic) {
			if (prclin.getPatient() != null) {
				if (prclin.getPatient().getUsername().equals(username)) {
					clinicsFromPrclinic.add(prclin.getPharmacy());
				}
			}
		}
		for (Pharmacy c : clinics) {
			if (!clinicsFromPrclinic.contains(c)) {
				PatientRatedPharmacy noviPrclinic = new PatientRatedPharmacy();
				noviPrclinic.setOcena(0);
				noviPrclinic.setPatient(patient);
				noviPrclinic.setPharmacy(c);
				prcs.save(noviPrclinic);
			}
		}

		List<PatientRatedPharmacy> prclinic1 = prcs.findAll();
		for (PatientRatedPharmacy p : prclinic1) {
			if (p.getPatient().getUsername().equals(username)) {
				ret.add(p);
			}
		}
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/rateChange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PatientRatedDoctorDTO> rateChange(
			@RequestBody PatientRatedDoctorDTO patientRatedDoctorDTO) {
		List<PatientRatedDoctor> prd = prds.findAll();
		Doctor doctor = (Doctor) mss.findByUsername(patientRatedDoctorDTO.getDoctorUsername());
		int suma = 0;
		int kolikoIhIma = 0;
		for (PatientRatedDoctor p : prd) {
			if (p.getPatient().getUsername().equals(patientRatedDoctorDTO.getPatientUsername())) {
				if (p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())) {
					p.setOcena(patientRatedDoctorDTO.getOcena());
					prds.save(p);
				}
			}
		}
		for (PatientRatedDoctor p : prd) {
			if (p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())) {
				suma += p.getOcena();
				kolikoIhIma++;
			}
		}
		doctor.setReview(suma / kolikoIhIma);
		mss.save(doctor);
		return new ResponseEntity<>(patientRatedDoctorDTO, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/rateChangePharmacy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PatientRatedClinicDTO> rateChangePharmacy(
			@RequestBody PatientRatedClinicDTO patientRatedClinicDTO) {
		List<PatientRatedPharmacy> prclinic = prcs.findAll();
		Pharmacy clinic = cs.findByName(patientRatedClinicDTO.getClinicName());
		int suma = 0;
		int kolikoIhIma = 0;
		for (PatientRatedPharmacy p : prclinic) {
			if (p.getPatient().getUsername().equals(patientRatedClinicDTO.getPatientUsername())) {
				if (p.getPharmacy().getName().equals(patientRatedClinicDTO.getClinicName())) {
					p.setOcena(patientRatedClinicDTO.getOcena());
					prcs.save(p);
				}
			}
		}

		for (PatientRatedPharmacy p : prclinic) {
			if (p.getPharmacy().getName().equals(patientRatedClinicDTO.getClinicName())) {
				suma += p.getOcena();
				kolikoIhIma++;
			}
		}
		clinic.setRating(suma / kolikoIhIma);
		cs.save(clinic);
		return new ResponseEntity<>(patientRatedClinicDTO, HttpStatus.OK);
	}
}
