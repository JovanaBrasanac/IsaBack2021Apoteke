package com.contoller;

import com.dto.AppointmentDTO;
import com.dto.CalendarEventsDTO;
import com.dto.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

	// Najcesce koriscena notacija za povezivanje zavisnosti
	@Autowired
	private DoctorService ds;
	@Autowired
	private RequestAppointmentService ras;
	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	public AppointmentService as;
	@Autowired
	private PharmacyService cs;
	@Autowired
	private PatientService ps;
	@Autowired
	private MedicalRecordService mrs;
	@Autowired
	private EmailService es;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private DiagnosisService dis;
	@Autowired
	private DrugService drs;
	@Autowired
	private RecipeService rs;
	@Autowired
	private AppointmentTypeService ats;
	@Autowired
	private EmailService emailService;

	// Lista slobodnih dermatologa
	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/availableDermatologists/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Doctor> availableDermatologysts(@RequestBody AppointmentDTO appointmentDTO, @PathVariable String username) throws ParseException {

		PharmacyAdministrator ca = cas.findByUsername(username);
		Pharmacy c = cs.findById(ca.getPharmacy().getId());
		PharmacyDTO apotekaDTO = new PharmacyDTO(c);
		apotekaDTO.setId(c.getId());
		appointmentDTO.setPharmacy(apotekaDTO);	
		return as.availableDermatologists(appointmentDTO);
		
	}


	@CrossOrigin(origins = "http//localhost:4200")
	@RequestMapping(value = "/api/appointments-res-rooms/{cadmin}", method = RequestMethod.GET)
	// @PathVariable identifikacija resursa na koji se operacija odnosi
	public List<RequestAppointment> getResRoom(@PathVariable String cadmin) {
		List<RequestAppointment> appointments = new ArrayList<>();
		List<RequestAppointment> ret = new ArrayList<RequestAppointment>();
		PharmacyAdministrator ca;
		try {
			ca = cas.findByUsername(cadmin);
		} catch (Exception e) {
			System.out.println("Nije ulogovan admin klinike!");
			return null;
		}
		return ret;
	}
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/deleteApp/{username}", method = RequestMethod.GET)
	public Appointment deleteApp(@PathVariable String username) {
		
		System.out.println("USAO SAM U DELETE");
		Appointment app = as.findById(Long.valueOf(username));
		Appointment app2 = new Appointment();
		app.setDate("0000-00-00T00:00");
		System.out.println("provera pronadjenog appa" + app.getId());
		System.out.println(app.getDate());
		System.out.println("USAO SAM U TRY");
		as.delete(app);
		System.out.println("PROVERA CUVANJA: " + app2.getDate());
		
		return app2;

	}
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/cancelApp/{username}", method = RequestMethod.GET)
	public Appointment cancelApp(@PathVariable String username) {
		
		System.out.println("USAO SAM U CANCEL");
		Appointment app = as.findById(Long.valueOf(username));
		Appointment app2 = new Appointment();
		app.setPatient(null);
		app2 = as.save(app);
		System.out.println("PROVERA CUVANJA: " + app2.getDate());
		
		return app2;

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-room-app", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody povratni tip metode
	public @ResponseBody ResponseEntity<Appointment> addApp(@RequestBody AppointmentDTO appointment) {
		// Transakcija se vrsi u servisu
		Appointment appointment1 = null;
		try {
			appointment1 = as.acceptAppointment(appointment);
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Appointment> addRequestApp(@RequestBody AppointmentDTO appointment) {
		
		Appointment appointmentOff = new Appointment();
		appointmentOff.setDate(appointment.getDate());
		appointmentOff.setDescription(appointment.getDescription());
		appointmentOff.setDuration(appointment.getDuration());
		Patient pa = new Patient();

		try {
			pa = ps.findByUsername(appointment.getPatient());
			Long paID = pa.getId();

			MedicalRecord mr = mrs.findByPatientId(paID);
			appointmentOff.setMedicalRecord(mr);
			appointmentOff.setPatient(appointment.getPatient());
		} catch (Exception e) {
		}
		appointmentOff.setDoctorUsername(appointment.getDoctorUsername());
		Doctor doctor = ds.findByUsername(appointment.getDoctorUsername());
		appointmentOff.setDoctor(doctor);
		
		AppointmentType at = ats.findById(Long.valueOf(1));
		
		appointmentOff.setType2(at);
		
		Appointment saved = new Appointment();
		
		try {
			saved = as.save(appointmentOff);
		} catch (Exception e) {
			System.out.println("Transakcija nije uspela!");
			return null;
		}
		// Slanje mail-a
		
		try {
			es.sendNotificaitionAsync4(pa);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata");
		}
			
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	// Dodavanje predefinisanih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestAppFAST/{username}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Appointment> addRequestAppo(@RequestBody AppointmentDTO appointment,
			@PathVariable String username) {


		Doctor doctor = ds.findByUsername(appointment.getDoctorUsername());
		
		System.out.println("ID doktora ili ime doktora:" + doctor.getId() + doctor.getUsername());
		DoctorDTO doctorDTO = new DoctorDTO(doctor);
		appointment.setDoctor(doctorDTO);
		
		Appointment appointment1 = new Appointment(appointment.getDate(),
		appointment.getDescription(), appointment.getDuration());
		PharmacyAdministrator ca = cas.findByUsername(username);
		Pharmacy c = cs.findById(ca.getPharmacy().getId());
		appointment1.setPharmacy(c);
		appointment1.setPrice(appointment.getPrice());
		appointment1.setDoctor(doctor);
		appointment.setDoctorUsername(doctor.getUsername());
		List<AppointmentType> typeApp = ats.findAll();
		
		for (AppointmentType a : typeApp) {
			if (a.getId() == 2) {
				System.out.println(a.getId());
				appointment1.setType2(a);
			}
		}
		as.save(appointment1);
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/add-requestApp-from-patient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppFromPatient(
			@RequestBody AppointmentDTO appointment) {

		RequestAppointment appointment1 = null;
		try {
			appointment1 = as.addRequestApp(appointment);
			if (appointment1 == null) {
				return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}

	// Pronalazenje svih zahteva za pregled
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentRequests", method = RequestMethod.GET)
	public List<RequestAppointment> getAppointmentReq() {
		return ras.findAll();
	}

	// Vracanje svih zavrsenih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointments/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointments(@PathVariable String username) {

		List<Appointment> ret = new ArrayList<>();
		List<Appointment> sviPregledi = as.findAll();
		for (Appointment a : sviPregledi) {
			if (a.isFinished()) {
				ret.add(a);
			}
		}
		return ret;
	}
	
	// Vracanje svih nezavrsenih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentsNonF/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsNonF(@PathVariable String username) {
		
		System.out.println("USAO SAM U NONF");

		List<Appointment> ret = new ArrayList<>();
		List<Appointment> sviPregledi = as.findAll();
		for (Appointment a : sviPregledi) {
			if (!(a.isFinished())) {
				ret.add(a);
			}
		}
		return ret;
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		
		System.out.println("USAO SAM U TEST");
		return "TEST";
	}
	
	

	// Vracanje svih zakazanih pregleda
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentsUnfinished/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsUnfinished(@PathVariable String username) {

		List<Appointment> ret = new ArrayList<>();
		List<Appointment> sviPregledi = as.findAll();
		for (Appointment a : sviPregledi) {
			if (!a.isFinished()) {
				ret.add(a);
				System.out.println(a.getType2().getName());
			}
		}
		return ret;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/all-requestAppointments/{usernameAdmin}", method = RequestMethod.GET)
	public List<RequestAppointment> getAllRequestAppointmentsOfClinic(@PathVariable String usernameAdmin) {

		List<RequestAppointment> apps = ras.findAll();
		List<RequestAppointment> ret = new ArrayList<>();
		PharmacyAdministrator clinicAdministrator = cas.findByUsername(usernameAdmin);
		try {
			for (RequestAppointment app : apps) {
				if (app.getPharmacy().getId().equals(clinicAdministrator.getPharmacy().getId())) {
					ret.add(app);
				}
			}
		} catch (Exception e) {
		}

		return ret;
	}

	//Pronalazenje pregleda pacijenta
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/all-patient-appointment/{username}", method = RequestMethod.GET)
	public List<Appointment> getAllAppointments(@PathVariable String username) {

		List<Appointment> apps = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		try {
			for (Appointment app : apps) {
				if (app.getPatient().equals(username))
					ret.add(app);
			}
		} catch (Exception e) {
		}
		return ret;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointments2() {
		return as.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAppointmentsMR/{username}", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsMR(@PathVariable String username) {
		return as.findAll();
	}

	// Uzimanje svih pregleda za kalendar
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getAllAppointments/{doctor}", method = RequestMethod.GET)
	public List<CalendarEventsDTO> getAllAppointmentsDoctor(@PathVariable String doctor) throws ParseException {
		List<Appointment> lista = as.findAll();
		List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();

		for (Appointment app : lista) {
			if (app.getDoctor() != null) {
				if (app.getDoctor().getUsername().equals(doctor)) {
					String title = "";
					try {
						Patient patient = ps.findByUsername(app.getPatient());
						String finished = "";
						if (app.isFinished())
							finished = "FINISHED";
						else
							finished = "AVAILABLE";
						title = app.getId() + "\n" + "pregled" + "\n" + app.getDescription() + "\n"
								+ patient.getFirstName() + " " + patient.getLastName() + "\n" + finished + "\n"
								+ patient.getUsername();
					} catch (Exception e) {
						title = app.getDescription() + "\nNema pacijenata.";
					}

					String color = "green";
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					Date date = dateFormat.parse(app.getDate());
					long millis = date.getTime();
					millis += app.getDuration() * 60 * 60 * 1000;
					String endDate = dateFormat.format(millis);

					CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(),
							color);
					eventsDTOS.add(eventsDTO);
				}
			}
		}
		Doctor doc = ds.findByUsername(doctor);
		return eventsDTOS;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/getAllAppointments-app/{n}", method = RequestMethod.GET)
	public List<CalendarEventsDTO> getAllAppointmentsN(@PathVariable String n) throws ParseException {
		List<Appointment> lista = as.findAll();
		List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();
		Pharmacist nur = (Pharmacist) mss.findByUsername(n);
		for (Appointment app : lista) {
			if (app.getDoctor().getPharmacy().getId() == nur.getPharmacy().getId()) {
				String title = "";
				try {
					Patient patient = ps.findByUsername(app.getPatient());
					title = app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
				} catch (Exception e) {
					title = app.getDescription() + "\nNema pacijenata.";
				}

				String color = "green";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Date date = dateFormat.parse(app.getDate());
				long millis = date.getTime();
				millis += app.getDuration() * 60 * 60 * 1000;
				String endDate = dateFormat.format(millis);

				CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
				eventsDTOS.add(eventsDTO);
			}
		}
		return eventsDTOS;
	}

	//Izvestaji
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/set-app-report", method = RequestMethod.POST)
	public void reportAppointment(@RequestBody ReportAppointmentDTO reportAppointmentDTO) {
		Appointment app2 = new Appointment();
		Appointment app = as.findById(reportAppointmentDTO.getAppointment().getId());
		app2.setId(reportAppointmentDTO.getAppointment().getId());
		app.setInfo(reportAppointmentDTO.getAppointment().getInfo());

		Diagnosis diagnosis = dis.findById(reportAppointmentDTO.getDiagnosis().getId());
		app.setDiagnosis(diagnosis);
		Recipe recipe = new Recipe().builder().authenticated(false)
				.description(reportAppointmentDTO.getRecipe().getDescription()).appointment(app).build();

		Set<Drug> drugs = new HashSet<>();
		for (Long id : reportAppointmentDTO.getRecipe().getDrugs()) {
			Drug drug = drs.findById(id);
			drugs.add(drug);
		}
		recipe.setDrug(drugs);
		app.setRecipe(recipe);
		Recipe r = rs.save(recipe);
		Appointment app1 = as.save(app);
	}

	//Dobavljanje starih medicinskih izvestaja
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-old-app-report/{doctor}", method = RequestMethod.GET)
	private List<Appointment> getOldAppointments(@PathVariable String doctor) {
		List<Appointment> appointments = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		for (Appointment app : appointments) {
			if (app.getDoctor().getUsername().equals(doctor) && app.isFinished())
				ret.add(app);
		}
		return ret;
	}

	@RequestMapping(value = "/getAlreadyCreatedAppointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> getAlreadyCreatedAppointments() throws ParseException {
		List<Appointment> lista = as.findAll();
		List<Appointment> ret = new ArrayList<>();
		for (Appointment a : lista) {
			if (a.getPatient() == null) {
				ret.add(a);
				System.out.println("Apoteka je: " + a.getPharmacy().getName());
			}
		}
		return ret;
	}

	private boolean checkTime(String date, List<Appointment> appointments) {
		boolean available = true;

		for (Appointment appointment : appointments) {
			if (available) {
				if (appointment.getDate().equals(date)) {
					System.out.println("Pregled nije moguc " + appointment.getDate());
					available = false;
				}
			}
		}
		return available;
	}

	@Scheduled(cron = "${greeting.cron}")
	private void systemReservation2() throws ParseException, InterruptedException {
		List<RequestAppointment> allRequestAppointments = ras.findAll();
		List<RequestAppointment> requestsWithoutRoom = new ArrayList<>();

		for (RequestAppointment s : requestsWithoutRoom) {
			RequestAppointment req = ras.findById(s.getId());

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date = dateFormat.parse(req.getDate());
			long startSurgery = date.getTime();

			boolean nadjenaSoba = false;
			while (nadjenaSoba == false) {
				String newDate = dateFormat.format(startSurgery);
			}
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/get-report-info/{id}", method = RequestMethod.GET)
	private ReportAppointmentDTO getOldAppointmentsInfo(@PathVariable Long id) {
		Appointment appointment = as.findById(id);
		ReportAppointmentDTO reportAppointmentDTO = new ReportAppointmentDTO();
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setPatient(appointment.getPatient());
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setInfo(appointment.getInfo());
		reportAppointmentDTO.setAppointment(appointmentDTO);
		DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
		Diagnosis d = dis.findById(appointment.getDiagnosis().getId());
		diagnosisDTO.setDescription(d.getDescription());
		diagnosisDTO.setName(d.getName());
		diagnosisDTO.setId(d.getId());
		reportAppointmentDTO.setDiagnosis(diagnosisDTO);
		RecipeDTO recipeDTO = new RecipeDTO();
		Recipe r = rs.findById(appointment.getRecipe().getId());
		recipeDTO.setDescription(r.getDescription());
		recipeDTO.setId(r.getId());
		Set<Drug> drugs = (Set<Drug>) r.getDrug();
		List<Long> recipeDrug = new ArrayList<>();
		for (Drug drug : drugs) {
			recipeDrug.add(drug.getId());
		}
		recipeDTO.setDrugs(recipeDrug);
		reportAppointmentDTO.setRecipe(recipeDTO);
		return reportAppointmentDTO;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/scheduleApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Appointment> scheduleApp(@RequestBody AppointmentDTO appointment) {

		Appointment appointment1 = null;
		try {
			appointment1 = as.schedule(appointment);
		} catch (Exception e) {
			return new ResponseEntity<>(appointment1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(appointment1, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addAppointment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Appointment> addApointment(@RequestBody AppointmentDTO appointment) {

		Appointment appointmentOff = new Appointment();
		appointmentOff.setDate(appointment.getDate());
		appointmentOff.setDescription(appointment.getDescription());
		appointmentOff.setDuration(appointment.getDuration());
		Patient pa = new Patient();

		try {
			pa = ps.findByUsername(appointment.getPatient());
			Long paID = pa.getId();

			MedicalRecord mr = mrs.findByPatientId(paID);
			appointmentOff.setMedicalRecord(mr);
			appointmentOff.setPatient(appointment.getPatient());
		} catch (Exception e) {
		}
		appointmentOff.setDoctorUsername(appointment.getDoctorUsername());
		Doctor doctor = ds.findByUsername(appointment.getDoctorUsername());
		appointmentOff.setDoctor(doctor);
		
		AppointmentType at = ats.findById(Long.valueOf(1));
		
		appointmentOff.setType2(at);
		
		Appointment saved = new Appointment();
		
		try {
			saved = as.save(appointmentOff);
		} catch (Exception e) {
			System.out.println("Transakcija nije uspela!");
			return null;
		}
		// Slanje mail-a
		
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(pa.getEmail());
        registrationEmail.setSubject("Termin za pregled kod farmaceuta");
        registrationEmail.setText("Uspesno ste zakazali pregled kod farmaceuta!");
        registrationEmail.setFrom("isa2021mail@gmail.com");
        emailService.sendEmail(registrationEmail);
		
			
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
