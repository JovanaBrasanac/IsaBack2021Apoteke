package com.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.PharmacyAdministratorDTO;
import com.dto.PharmacyDTO;
import com.model.Pharmacy;
import com.model.PharmacyAdministrator;
import com.repository.PharmacyAdministratorRepository;
import com.repository.PharmacyRepository;
import com.service.PharmacyAdministratorService;
import com.service.PharmacyService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PharmacyAdministratorController {

	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	private PharmacyService cs;
	@Autowired
	private PharmacyRepository cr;
	@Autowired
	private PharmacyAdministratorRepository car;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-admin/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addAdministrator(@RequestBody PharmacyAdministratorDTO clinicAdministratorDTO, @PathVariable long id) {
		Pharmacy clinic = cs.findById(id);
		PharmacyAdministrator ca = new PharmacyAdministrator();
		ca.setUsername(clinicAdministratorDTO.getUsername());
		ca.setPassword(clinicAdministratorDTO.getPassword());
		ca.setEmail(clinicAdministratorDTO.getEmail());
		ca.setAddress(clinicAdministratorDTO.getAddress());
		ca.setCity(clinicAdministratorDTO.getCity());
		ca.setCountry(clinicAdministratorDTO.getCountry());
		ca.setJmbg(clinicAdministratorDTO.getJmbg());
		ca.setFirstName(clinicAdministratorDTO.getFirstName());
		ca.setLastName(clinicAdministratorDTO.getLastName());
		ca.setMobileNumber(clinicAdministratorDTO.getMobileNumber());
		ca = car.save(ca);
		clinic.getPharmacyAdministrator().add(ca);
		ca.setPharmacy(clinic);
		car.save(ca);
		cr.save(clinic);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/cadmin/{username}", method = RequestMethod.GET)
	public PharmacyAdministrator getAdmin(@PathVariable String username) {
		return cas.findByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getMyClinic/{username}", method = RequestMethod.GET)
	public PharmacyDTO getPharmacy(@PathVariable String username) {
		PharmacyAdministrator admin = cas.findByUsername(username);
		Pharmacy clinic = cs.findById(admin.getPharmacy().getId());
		PharmacyDTO pharmacyDTO = new PharmacyDTO(clinic);
		pharmacyDTO.setId(clinic.getId());
		return pharmacyDTO;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/adminChangeInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<PharmacyAdministratorDTO> changeInfo(@RequestBody PharmacyAdministratorDTO mdNovi) {
		PharmacyAdministrator md = (PharmacyAdministrator) cas.findByUsername(mdNovi.getUsername());
		if (md != null) {
			md.setPassword(mdNovi.getPassword());
			md.setFirstName(mdNovi.getFirstName());
			md.setLastName(mdNovi.getLastName());
			md.setCity(mdNovi.getCity());
			md.setCountry(mdNovi.getCountry());
			md.setEmail(mdNovi.getEmail());
			md.setMobileNumber(mdNovi.getMobileNumber());
			cas.save(md);
		} else {

		}
		PharmacyAdministratorDTO d = new PharmacyAdministratorDTO(md);
		return new ResponseEntity<>(d, HttpStatus.OK);
	}
}
