package com.contoller;

import com.dto.DrugDTO;
import com.dto.PatientDTO;
import com.dto.PurchaseOrderDTO;
import com.model.Appointment;
import com.model.AppointmentType;
import com.model.Drug;
import com.model.DrugReservation;
import com.model.Patient;
import com.model.PharmacyAdministrator;
import com.model.PurchaseOrder;
import com.service.DrugReservationService;
import com.service.DrugService;
import com.service.PatientService;
import com.service.PharmacyAdministratorService;
import com.service.PurchaseOrderService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PurchaseOrderController {

	@Autowired
	private DrugService drs;
	
	@Autowired
	public PatientService ps;
	
	@Autowired
	public DrugReservationService drrs;
	
	@Autowired
	public PharmacyAdministratorService pas;

	@Autowired
	public PurchaseOrderService pos;
	
	//Dodavanje porudzbenice
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-purchase-order/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void savePurchaseOrder(@RequestBody PurchaseOrderDTO orderDTO, @PathVariable String username ) {
		PurchaseOrder order = new PurchaseOrder();
		
		PharmacyAdministrator padmin = pas.findByUsername(username);
		
		order.setDrugs(orderDTO.getDrugs());
		order.setPharmacyAdmin(padmin);

		PurchaseOrder ret = pos.save(order);
	}
	
    //Pronalazenje svih narudzbenica
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/getPurchaseOrders/{id}", method= RequestMethod.GET)
	public List<PurchaseOrder> getTypes(@PathVariable int id){

		
	List<PurchaseOrder> list = pos.findAll();
	List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
	
	for (PurchaseOrder a : list) {
		if(a.getPharmacyAdmin().getPharmacy().getId() == id ) {	
			orders.add(a);
		}
	}
		
    return orders;
	}
	
	/*
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
		/*
		drug.setId(Long.valueOf(drugDTO.getId()));
		drug.setName(drugDTO.getName());
		drug.setPrice(drugDTO.getPrice());
		drug.setQuantity(drugDTO.getQuantity());
		*/
		//drug = drs.save(drug);
		/*
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
	*/
}
