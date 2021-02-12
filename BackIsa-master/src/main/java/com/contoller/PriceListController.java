package com.contoller;

import com.dto.PriceListDTO;
import com.model.Appointment;
import com.model.Pharmacy;
import com.model.PharmacyAdministrator;
import com.model.PriceList;
import com.repository.PharmacyAdministratorRepository;
import com.service.PharmacyAdministratorService;
import com.service.PharmacyService;
import com.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PriceListController {

	@Autowired
	private PriceListService pls;
	@Autowired
	private PharmacyService cs;
	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	private PharmacyAdministratorRepository car;
	
	//Vraca listu cena
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getPrices/{id}", method = RequestMethod.GET)
	public List<PriceList> getPrices(@PathVariable int id) {
		
		Pharmacy clinic = cs.findById(id);
		
		List<PriceList> list = pls.findAll();
		List<PriceList> prices = new ArrayList<PriceList>();
		
		for (PriceList a : list) {
			if (a.getPharmacy().getId() == clinic.getId()) {
				prices.add(a);
			}
		}
		
		return prices;
	}

	//Dodavanje cene
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/add-price", method = RequestMethod.POST)
	public void addPrice(@RequestBody PriceList priceList) {
		pls.save(priceList);
	}

	//Menjanje cene
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/changePrice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PriceList> changeInfo(@RequestBody PriceListDTO priceList) {
		
		
		PriceList priceList2 = pls.findById(Long.valueOf(priceList.getId()));
		
		PriceList priceList3 = new PriceList();
		if (priceList2 != null) {
			priceList2.setPrice(priceList.getPrice());
			priceList3 = pls.save(priceList2);
		} else {
		}
		return new ResponseEntity<>(priceList3, HttpStatus.OK);
	}
}
