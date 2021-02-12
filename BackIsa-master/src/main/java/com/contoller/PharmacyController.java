package com.contoller;

import com.dto.PharmacyDTO;
import com.dto.PromotionDTO;
import com.dto.DoctorDTO;
import com.model.*;
import com.service.AppointmentService;
import com.service.DrugService;
import com.service.PharmacyAdministratorService;
import com.service.PharmacyService;
import com.service.PromotionService;
import com.service.MedicalStaffService;
import com.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PharmacyController {

	@Autowired
	private PharmacyService cs;
	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	private DrugService ds;
	@Autowired
	private PatientService pats;
	@Autowired
	private PromotionService ps;
	@Autowired
	MedicalStaffService mss;
	@Autowired
	AppointmentService as;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-pharmacy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Pharmacy> addPharmacy(@RequestBody PharmacyDTO clinicDTO) {
		Pharmacy clinic = new Pharmacy();
		clinic.setName(clinicDTO.getName());
		clinic.setDescription(clinicDTO.getDescription());
		clinic.setAddress(clinicDTO.getAddress());
		clinic.setPricelist(clinicDTO.getPricelist());
		clinic.setProfit(clinicDTO.getProfit());
		cs.save(clinic);
		return new ResponseEntity<>(clinic, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-promotion/{username}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addPromotion(@RequestBody PromotionDTO promotionDTO, @PathVariable("username") String username ) {
		
		System.out.println("USAO sam u dodavanje promocije");
		Pharmacy pharmacy = new Pharmacy();
		Promotion promotion = new Promotion();
		PharmacyAdministrator admin = cas.findByUsername(username);
		pharmacy = cs.findById(admin.getPharmacy().getId());
		promotion.setPharmacy(pharmacy);
		promotion.setDate(promotionDTO.getDate());
		promotion.setDescription(promotionDTO.getDescription());
		Promotion promotionSave = ps.save(promotion);
		
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-pharmacys", method = RequestMethod.GET)
	public ResponseEntity<List<PharmacyDTO>> getPharmacy() {
		List<Pharmacy> clinics = cs.findAll();
		List<PharmacyDTO> clinisDTOS = new ArrayList<>();
		for (Pharmacy c : clinics) {
			PharmacyDTO CDTO = new PharmacyDTO(c.getId(), c.getName(), c.getAddress(), c.getPricelist(), c.getDescription(),
					c.getProfit(), c.getRating());
			clinisDTOS.add(CDTO);
		}
		return new ResponseEntity<List<PharmacyDTO>>(clinisDTOS, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-search-pharmacys/{date}/{type}", method = RequestMethod.GET)
	public ResponseEntity getSearchPharmacy(@PathVariable("date") String date, @PathVariable("type") String type) {

		try {
			List<Pharmacy> clinics = cs.getSearchClinics(date, type);
			List<PharmacyDTO> clinisDTOS = new ArrayList<>();
			for (Pharmacy c : clinics) {
				PharmacyDTO CDTO = new PharmacyDTO(c.getId(), c.getName(), c.getAddress(), c.getPricelist(),
						c.getDescription(), c.getProfit(), c.getRating());
				clinisDTOS.add(CDTO);
			}
			return new ResponseEntity<>(clinisDTOS, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Neispravno", HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getMedications/{id}", method = RequestMethod.GET)
	public List<Drug> getSearchDrugs(@PathVariable("id") String id) {

		System.out.println("Usao sam u getMed");
		System.out.println("Id je: " + id);
		Pharmacy pharmacy = cs.findById(Long.valueOf(id));
		
		System.out.println("PharmacyID: " + pharmacy.getId());
		
		List<Drug> drugs = ds.findAll();
		List<Drug> ret = new ArrayList<Drug>();
		
		for (Drug drug : drugs) {
			
			if(drug.getPharmacy().getId() == Long.valueOf(id)) {
				
				ret.add(drug);
			}

		}
		
		return ret;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getPromotions/{id}", method = RequestMethod.GET)
	public List<Promotion> getPromotions(@PathVariable("id") String id) {

		System.out.println("Usao sam u getPromotions");
		System.out.println("Id je: " + id);
		Pharmacy pharmacy = cs.findById(Long.valueOf(id));
		
		System.out.println("PharmacyID: " + pharmacy.getId());
		
		List<Promotion> promotions = ps.findAll();
		List<Promotion> ret = new ArrayList<Promotion>();
		
		for (Promotion promotion : promotions) {
			
			if(promotion.getPharmacy().getId() == Long.valueOf(id)) {
				
				ret.add(promotion);
			}

		}
		
		return ret;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-search-doctors/{date}/{imeKlinike}/{tipPregleda}", method = RequestMethod.GET)
	public ResponseEntity getSearchPharmacists(@PathVariable("date") String date,
			@PathVariable("imeKlinike") String imeKlinike, @PathVariable("tipPregleda") String tipPregleda) {

		try {
			List<Doctor> doctors = cs.getSearchDoctor(date, imeKlinike, tipPregleda);
			List<DoctorDTO> doctorsDTO = new ArrayList<>();
			for (Doctor doc : doctors) {
				DoctorDTO DDTO = new DoctorDTO(doc.getId(), doc.getUsername(), doc.getPassword(), doc.getFirstName(),
						doc.getLastName());
				DDTO.setRole(doc.getRole());
				DDTO.setAddress(doc.getAddress());
				DDTO.setCity(doc.getCity());
				DDTO.setCountry(doc.getCountry());
				DDTO.setEmail(doc.getEmail());
				DDTO.setJmbg(doc.getJmbg());
				DDTO.setMobileNumber(doc.getMobileNumber());
				DDTO.setPocetakRadnogVremena(doc.getPocetakRadnogVremena());
				DDTO.setKrajRadnogVremena(doc.getKrajRadnogVremena());
				doctorsDTO.add(DDTO);
			}
			return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Neispravno", HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-PharmacyAdmins/{id}", method = RequestMethod.GET)
	public List<PharmacyAdministrator> getPharmacyAdmins(@PathVariable long id) {
		List<PharmacyAdministrator> ret = cas.findByPharmacyId(id);
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/PharmacyChangeInfo/{name}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<PharmacyDTO> changeInfo(@RequestBody PharmacyDTO newClinic,
			@PathVariable String name) {
		// Transakcija se vrsi u servisu
		Pharmacy c = new Pharmacy();
		try {
			c = cs.changeInfo(newClinic, name);
		} catch (Exception e) {
			PharmacyDTO PharmacyDTO = new PharmacyDTO(c);
			return new ResponseEntity<>(PharmacyDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		PharmacyDTO PharmacyDTO = new PharmacyDTO(c);
		return new ResponseEntity<>(PharmacyDTO, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/pretplatiSe/{promotionID}/{username}", method = RequestMethod.GET)
	public Promotion pretplata(@PathVariable String promotionID,@PathVariable String username) {
		
		Patient patient = pats.findByUsername(username);
		Promotion promotion = ps.findById(Long.valueOf(promotionID));
		Set<Patient> patients = promotion.getPatients();
		patients.add(patient);
		promotion.setPatients(patients);
		Promotion promotionSave = ps.save(promotion);
		return promotionSave;
	}
}
