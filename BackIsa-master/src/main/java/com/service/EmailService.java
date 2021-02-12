package com.service;

import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    private JavaMailSender mailSender;

	@Autowired
	private JavaMailSender jms;
	@Autowired
	private Environment env;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

	@Async
	public void sendNotificaitionAsync(Patient user, ConfirmationTokenRegistration confirmationToken)
			throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Potvrda registracije!");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setText("Da biste potvrdili svoj nalog, kliknite na sledeci link : "
				+ "http://localhost:4200/confirm-account?token=" + confirmationToken.getConfirmationToken());
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync2(String email, String message) throws MailException, InterruptedException {

		String email2 = email + ".com";
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email2);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijen zahtev");

		mail.setText("Postovani, " + ",\n\nVas zahtev je odbijen.\n\n" + message);
		try {
			jms.send(mail);
		} catch (Exception e) {
		}
	}

	@Async
	public void sendNotificaitionAsync3() throws MailException, InterruptedException {

	}

	@Async
	public void sendNotificaitionAsyncc3(PharmacyAdministrator cadmin) throws MailException, InterruptedException {

	}

	@Async
	public void sendNotificaitionAsync4(Patient patient) throws MailException, InterruptedException {

	}

	@Async
	public void sendPatientNotificaition2(Appointment surgery, Patient patient)
			throws MailException, InterruptedException {

	}

	@Async
	public void sendNotificaitionAsync5() throws MailException, InterruptedException {

	}

	@Async
	public void sendNotificaitionAsync6(String message) throws MailException, InterruptedException {

	}

	@Async
	public void sendPatientNotificaition7(Appointment surgery, Patient patient)
			throws MailException, InterruptedException {

	}

	@Async
	public void sendPatientNotificaition7(Pharmacist nurse) throws MailException, InterruptedException {

	}

	@Async
	public void sendNotificaitionAsync8(PharmacyAdministrator ca) throws MailException, InterruptedException {

	}

	@Async
	public void sendPatientNotificaition9(Pharmacist nurse) throws MailException, InterruptedException {

	}

}