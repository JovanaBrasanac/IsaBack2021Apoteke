package com.service;

import com.dto.AppointmentDTO;
import com.model.*;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class AppointmentService implements AppointmentServiceInterface {

	//Najcesce koriscena notacija za povezivanje zavisnosti
	@Autowired
	public AppointmentRepository ar;
	@Autowired
	private DoctorService ds;
	@Autowired
	private RequestAppointmentService ras;
	@Autowired
	private MedicalStaffService mss;
	@Autowired
	private PatientService ps;
	@Autowired
	private MedicalRecordService mrs;
	@Autowired
	private AppointmentService as;
	@Autowired
	private EmailService es;
	@Autowired
	private PharmacyAdministratorService cas;
	@Autowired
	private PharmacyService cs;
	
	public Appointment save(Appointment appointment) {
		return ar.save(appointment);
	}

	public List<Appointment> findAll() {
		return ar.findAll();
	}

	public List<Appointment> findByFinished(Boolean finished) {
		return ar.findByFinished(finished);
	}

	public Appointment findByDate(String date) {
		return ar.findByDate(date);
	}
	
	public void deleteApp (Appointment app) {
		
	}
	
	public void deleteAppID (Long id) {
		ar.deleteById(id);
	}
	
	public Appointment findById(Long id) {
		return ar.findById(id).get();
	}

	public Appointment setFinished(Appointment app) {
		app.setFinished(true);
		Appointment savedApp = ar.save(app);
		return savedApp;
	}
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public Appointment cancelApp(Appointment app) {
		
		System.out.println("USAO U CANCEL APP");
		
		System.out.println(app.getDescription() + app.getDate());
		
		Appointment app2 = as.findById(app.getId());
		
		app2.setDate("0000-00-00T00:00");
		System.out.println(app2.getDescription() + app2.getDate() + app2.getId());
		Appointment savedApp = ar.save(app2);
		System.out.println("Sacuvana app: " + app2.getDate());
		return savedApp;
	}

	//rollbackFor oznacava za koje izuzetke ce se desiti roolback
	//REQUIRED prikljucuje metodu transakciji ili otvara novu ako transakcija ne postoji
	//READ_UNCOMMITTED eliminise problem poslednje izmene
	//Slanje mail-a za pregled pacijentu
	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public Appointment acceptAppointment(AppointmentDTO appointment) {
		Appointment appointment1 = new Appointment();
		appointment1.setDate(appointment.getDate());
		appointment1.setDescription(appointment.getDescription());
		appointment1.setDoctorUsername(appointment.getDoctorUsername());
		appointment1.setDuration(appointment.getDuration());
		appointment1.setType(appointment.getType());
		appointment1.setPatient(appointment.getPatient());
		appointment1.setDoctor((Doctor) mss.findByUsername(appointment.getDoctorUsername()));
		appointment1.setPrice(appointment.getPrice());
		Patient pa = ps.findByUsername(appointment.getPatient());
		Long paID = pa.getId();
		MedicalRecord mr = mrs.findByPatientId(paID);
		appointment1.setMedicalRecord(mr);
		as.save(appointment1);

		try {
			es.sendPatientNotificaition7(appointment1, pa);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata");
		}
		System.out.println(appointment.getId());
		RequestAppointment ra = ras.findById(appointment.getId());
		System.out.println(ra.getId());
		ras.delete(ra);
		return appointment1;
	}


	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public Appointment schedule(AppointmentDTO appointment) {
		Appointment appointment1 = ar.findById(appointment.getId()).get();
		Patient patient = ps.findByUsername(appointment.getPatient());
		MedicalRecord mr = mrs.findByPatientId(patient.getId());
		appointment1.setMedicalRecord(mr);
		appointment1.setPatient(appointment.getPatient());
		System.out.println(appointment1.getPatient());
		try {
			ar.save(appointment1);
		} catch (Exception e) {
			System.out.println("Transakcija nije uspela!");
			return null;
		}
		try {
			es.sendNotificaitionAsync4(patient);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata");
		}
		return appointment1;
	}
	

	@Transactional(rollbackFor = {
			RuntimeException.class }, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public RequestAppointment addRequestApp(AppointmentDTO appointment) {
		List<RequestAppointment> requestAppointments = ras.findAll();
		for (RequestAppointment reqApp : requestAppointments) {
			if (reqApp.getDate().equals(appointment.getDate())
					&& reqApp.getDoctor().getUsername().equals(appointment.getDoctorUsername())) {
				return null;
			}
		}

		RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(), appointment.getDate(),
				appointment.getDescription(), appointment.getDuration());
		Patient pa = ps.findByUsername(appointment.getPatient());
		Long paID = pa.getId();
		MedicalRecord mr = mrs.findByPatientId(paID);
		appointment1.setMedicalRecord(mr);
		appointment1.setDoctorUsername(appointment.getDoctorUsername());
		appointment1.setType(appointment.getType());

		Doctor doctor = ds.findByUsername(appointment.getDoctorUsername());
		appointment1.setDoctor(doctor);
		appointment1.setPharmacy(doctor.getPharmacy());
		ras.save(appointment1);
		try {
			PharmacyAdministrator ca = cas.findByPharmacyId(doctor.getPharmacy().getId()).get(0);
			es.sendNotificaitionAsync8(ca);
		} catch (Exception e) {
			System.out.println("Poruka nije poslata");
		}
		return appointment1;
	}

	//Pronalazenje pregleda po Id
	public Appointment findOne(Long id) {
		Optional<Appointment> appointment = ar.findById(id);
		if (appointment.isPresent()) {
			return appointment.get();
		} else {
			throw new ValidationException("Pregled ne postoji!");
		}
	}

	@Override
	public void delete(Appointment app) {
		// TODO Auto-generated method stub
		
		ar.delete(app);
		
	}
	
	public List<Doctor> availableDermatologists(AppointmentDTO appointment) throws ParseException {
		List<Doctor> ret = new ArrayList<>();
			
		System.out.println("Id je: " + appointment.getPharmacy().getId());
		
		Pharmacy c = cs.findById(Long.valueOf(appointment.getPharmacy().getId()));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = dateFormat.parse(appointment.getDate());

		// Izbor slobodnog dermatologa
		List<Doctor> doctors = ds.findAll();
		List<Doctor> doctorsPharmacy = new ArrayList<Doctor>();
		
		for (Doctor s : doctors) {

			if(s.getPharmacy().getId() == appointment.getPharmacy().getId() && s.getAppointmentType().getId() == 2) {
				
				doctorsPharmacy.add(s);
			}
			
		}
		
		
		for (Doctor doctor : doctorsPharmacy) {
			boolean nadjenaOperacijaKojaJeUTomTerminu = false;
			// Gledamo sa li je slobodan termin
			List<Appointment> appoitmentsDoc = as.findByDoctorId(doctor.getId());
			for (Appointment s : appoitmentsDoc) {
				if (nadjenaOperacijaKojaJeUTomTerminu == false) {
					// Uporedjivanje datuma
					if (appointment.getDate().equals(s.getDate())) {
						nadjenaOperacijaKojaJeUTomTerminu = true;
					}
				}
			}


			if (!nadjenaOperacijaKojaJeUTomTerminu) {
				
				ret.add(doctor);
			}
				
		}
		return ret;
	}

	@Override
	public List<Appointment> findByDoctorId(Long id) {
		
		List<Appointment> allApps = ar.findAll();
		List<Appointment> byDoctor = new ArrayList<Appointment>();
		
		for (Appointment s : allApps) {

			if(s.getDoctor().getId() == id)
			{
				byDoctor.add(s);
			}
		}
		
		return byDoctor;
	}

}
