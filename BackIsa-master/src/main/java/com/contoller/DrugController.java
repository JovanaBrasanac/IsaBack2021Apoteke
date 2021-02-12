package com.contoller;

import com.dto.DrugDTO;
import com.dto.PatientDTO;
import com.model.Appointment;
import com.model.AppointmentType;
import com.model.Drug;
import com.model.DrugReservation;
import com.model.Patient;
import com.service.DrugReservationService;
import com.service.DrugService;
import com.service.EmailService;
import com.service.PatientService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DrugController {

	@Autowired
	private DrugService drs;
	
	@Autowired
	public PatientService ps;
	
	@Autowired
	public DrugReservationService drrs;
	
	@Autowired
	private EmailService emailService;

	//Dodavanje lekova
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-drug", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveDrug(@RequestBody DrugDTO drugDTO) {
		Drug drug = new Drug();
		drug.setName(drugDTO.getName());
		drug.setPrice(drugDTO.getPrice());
		drug.setQuantity(drugDTO.getQuantity());
		drug = drs.save(drug);
	}
	
	@CrossOrigin
	@GetMapping("lekovi")
	public List<DrugDTO> getDrugs() {
		List<DrugDTO> drugDTOS = new ArrayList<>();
		for (Drug drug : drs.findAll()) {
			DrugDTO drugDTO = new DrugDTO();
			drugDTO.setName(drug.getName());
			drugDTO.setQuantity(drug.getQuantity());
			drugDTO.setPrice(drug.getPrice());
			drugDTOS.add(drugDTO);
		}
		return drugDTOS;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-drugReservation/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DrugReservation reserveDrug (@RequestBody DrugDTO drugDTO, @PathVariable String username) {
		
		System.out.println("ID leka je: " + drugDTO.getId());
		DrugReservation dRes = new DrugReservation();
		
		Drug drug = drs.findById(Long.valueOf(drugDTO.getId()));

		if(drug.getQuantity()<1) {
			
			System.out.println("Nema dovoljno ovog leka na stanju");
			return null;
			
		}
		
		int kolicina = drug.getQuantity();
		kolicina = kolicina-1;
		
		drug.setQuantity(kolicina);
		drs.save(drug);
		
		Patient patient = ps.findByUsername(username);
		dRes.setDrug(drug);
		dRes.setPatient(patient);
		
		DrugReservation drugR = drrs.save(dRes);
		
		System.out.println("drugR je: " + drugR.getDrug().getId() + drugR.getDrug().getName() + drugR.getPatient().getUsername());
		
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(patient.getEmail());
        registrationEmail.setSubject("Rezervacija leka");
        registrationEmail.setText("Uspesno ste rezervisali lek: " + drugR.getDrug().getName());
        registrationEmail.setFrom("isa2021mail@gmail.com");
        emailService.sendEmail(registrationEmail);
		
		return drugR;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/getDReservations/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DrugReservation> getDReservations (@PathVariable String username) {
		
		Patient patient = ps.findByUsername(username);
		
		List<DrugReservation> reservations = drrs.findAll();
		List<DrugReservation> ret = new ArrayList<DrugReservation>();
		
		for (DrugReservation a : reservations) {
			if (a.getPatient().getId() == patient.getId()) {
				ret.add(a);
				
			}
		}
		
		return ret;
	
	}
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/cancelDrugReservation/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DrugReservation cancelDReservations (@PathVariable int username) {
		
		DrugReservation dres = drrs.findById(Long.valueOf(username));
		DrugReservation dresRet = new DrugReservation();

		drrs.delete(dres);
		return dresRet;
	}
	
}
