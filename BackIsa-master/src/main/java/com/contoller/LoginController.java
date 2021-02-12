package com.contoller;

import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	private SystemAdministratorService ccas;
	@Autowired
	private PatientService ps;
	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	private MedicalStaffService mss;

	//Logovanje spram uloge u sistemu
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User postCCAByUsernameAndPassword(@RequestBody User user) {
		SystemAdministrator cca;
		try {
			cca = ccas.findByUsername(user.getUsername());
		} catch (Exception e) {
			cca = null;
		}
		String ret = "none";
		Patient pa = null;
		PharmacyAdministrator ca = null;
		Doctor doc = null;
		Pharmacist nur = null;
		MedicalStaff ms = null;

		if (cca == null) {

			try {
				pa = ps.findByUsername(user.getUsername());
			} catch (Exception e) {
				pa = null;
			}

			if (pa == null) {

				try {
					ca = cas.findByUsername(user.getUsername());
				} catch (Exception e) {
					ca = null;
				}

				if (ca == null) {

					try {
						ms = mss.findByUsername(user.getUsername());
					} catch (Exception e) {
						ms = null;
					}

					if (ms == null) {
						{
							user.setRole("NONE");
							return user;
						}
					} else {
						if (!user.getPassword().equals(ms.getPassword())) {
							user.setRole("NONE");
							return user;
						}
						else {
							
							if (ms.getRole().equals("nurse"))
								user.setRole("NURSE");
							else if (ms.getRole().equals("doctor"))
								user.setRole("PHARMACIST");
							
						}
						
					}

				} else {
					if (!user.getPassword().equals(ca.getPassword())) {
						user.setRole("NONE");
						return user;
					}
					user.setRole("PharmAdmin");
				}
			} else {
				if (!user.getPassword().equals(pa.getPassword())) {
					user.setRole("NONE");
					return user;
				} else if (user.getPassword().equals(pa.getPassword()) && pa.isEnabled()) {
					user.setRole("PA");
				}

			}
		} else {
			if (!user.getPassword().equals(cca.getPassword())) {
				user.setRole("NONE");
				return user;
			}
			user.setRole("SYSADMIN");
		}

		return user;
	}
}
