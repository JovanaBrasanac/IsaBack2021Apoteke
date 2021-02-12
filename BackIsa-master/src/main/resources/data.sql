INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACY_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');

INSERT INTO pharmacy_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, pharmacy_id) VALUES ('padmin', 'padmin', 'probaprobaisa@gmail.com', 'Padmin', 'Padmin', '354168465', 'Glavna 54', 'Beograd', 'Srbija', '0645865499', 1);
INSERT INTO pharmacy_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, pharmacy_id) VALUES ('padmin1', 'padmin1', 'probaprobaisa@gmail.com', 'Padmin1', 'Padmin1', '354168465', 'Glavna 20', 'Beograd', 'Srbija', '0645865499', 2);

INSERT INTO system_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('sysadmin', 'sysadmin', 'probaprobaisa@gmail.com', 'Admin', 'Adminic', '58768', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 1,true);
INSERT INTO system_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('asys', '123', 'probaprobaisa@gmail.com', 'Admin', 'Admin', '111111', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 0,true);
INSERT INTO system_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('asys1', '123', 'probaprobaisa@gmail.com', 'Admin', 'Admin', '111111', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 0,true);

INSERT INTO pharmacy (address, description, name, pricelist, profit, rating) VALUES ('Slavka Rodića 3, 21000 Novi Sad', 'Poverenje, sigurnost, dostupnost!', 'Jankovic', 0, 0, 0);
INSERT INTO pharmacy (address, description, name, pricelist, profit, rating) VALUES ('Pukovnika Milenka Pavlovića 1, 11273 Zemun', 'Uvek tu za vas', 'Zegin', 0, 0, 3);
INSERT INTO pharmacy (address, description, name, pricelist, profit, rating) VALUES ('Strazilovska 19a, 21000 Novi Sad', 'Negujte dobre navike', 'BENU', 0, 0, 4);
INSERT INTO pharmacy (address, description, name, pricelist, profit, rating) VALUES (' Beogradska 14, 11000 Beograd', 'Vec dve decenije tradicije!', 'Laurus', 0, 0, 4);
INSERT INTO pharmacy (address, description, name, pricelist, profit, rating) VALUES ('Cara Lazara 7, 21410 Futog', 'Vama na usluzi 00-24', 'Irisfarm', 0, 0, 4);

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 6', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent1', 1497,'Pacijent1', '0640589536', 'pacijent1', 'pacijent1', '1', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent2', 5097,'Pacijent2', '0640589536', 'p1', 'p1', '2', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7A', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent3', 25097,'Pacijent3', '0640589536', 'p2', 'p2', '3', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7B', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent4', 1497,'Pacijent4', '0640589536', 'p3', 'p3', '4', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7C', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent5', 5097,'Pacijent5', '0640589536', 'p4', 'p4', '5', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 8D', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Sale', 5097,'Sale', '0640589536', 'sale', 'sale', '6', true);

INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pocetak_radnog_vremena,kraj_radnog_vremena, pharmacy_id) VALUES ('2', 'D2', '123', 'probaprobaisa@gmail.com', 'Sestra', 'Medicinski', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Srbija', '06658651', 2, 'nurse',"07:00","14:00",1);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'farmaceut1', '123', 'probaprobaisa@gmail.com', 'farmaceut1', 'farmaceut1', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', 1, 'doctor', 1,"08:00","15:00", 1);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'f2', '123', 'probaprobaisa@gmail.com', 'farmaceut2', 'farmaceut2', '2855558', 'Bulevar Cara Dusana 5', 'Novi Sad', 'Serbia', '0655555565', 3, 'doctor', 2,"08:00","15:00", 1);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'd1', '123', 'probaprobaisa@gmail.com', 'dermatolog1', 'dermatolog1', '566987', 'Bulevar Cara Lazara 65', 'Novi Sad', 'Serbia', '0623566665', 0, 'doctor', 3,"10:00","18:00", 2);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'f3', '123', 'probaprobaisa@gmail.com', 'farmaceut3', 'farmaceut3', '1111558', 'Danila Kisa 5', 'Novi Sad', 'Serbia', '0644455565', 4, 'doctor', 1,"07:00","14:00", 1);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'd2', '123', 'probaprobaisa@gmail.com', 'dermatolog2', 'dermatolog2', '6546546', 'Glavna 123', 'Novi Sad', 'Serbia', '065684652', 4, 'doctor', 1,"10:00","18:00", 2);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, pharmacy_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES ('1', 'd3', '123', 'probaprobaisa@gmail.com', 'dermatolog3', 'dermatolog3', '6595026', 'Bulevar Oslobodjenja 13', 'Novi Sad', 'Serbia', '063585526', 4, 'doctor', 1,"10:00","18:00", 2);
INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena, pharmacy_id) VALUES ('2', 'D1', '123', 'probaprobaisa@gmail.com', 'Sestra1', 'Medicinovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', 2, 'nurse',"07:00","14:00",1);

INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('A', '+2.25', '185', '95',1);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('AB', '-2.25', '192', '82',2);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('0','-0.25', '156', '52',3);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('B+', '-0.0', '189', '72',4);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('B-', '-5.0', '150', '45',5);

INSERT INTO request_appointments (price,date, description, duration, patient, type,pharmacy_id,doctor_id,doctor_username) VALUES (500,'2021-01-27T16:00', 'Konsultacije kod farmaceuta u vezi leka', 2, 'p1', 'tip1',1,2,'farmaceut1');
INSERT INTO request_appointments (price,date, description, duration, patient, type,pharmacy_id,doctor_id,doctor_username) VALUES (300,'2021-01-23T10:00', 'Konsultacije kod farmaceuta u vezi kozne iritacije', 2, 'p2', 'tip1',1,2,'farmaceut1');
INSERT INTO request_appointments (price,date, description, duration, patient, type,pharmacy_id,doctor_id,doctor_username) VALUES (200,'2021-04-02T10:00', 'Redovna kontrola', 2, 'p2', 'tip1',2,3,'f2');

INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (2,1);
INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (2,2);
INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (3,3);

INSERT INTO appointments (price, date, description, duration, patient,finished, type, doctor_id, medical_record_id, doctor_username,type2_id,pharmacy_id) VALUES (1,'2020-02-05T16:00', 'cold', 2, 'p2', false,'Redovna kontrola', 2, 5, 'farmaceut1',1, 1);
INSERT INTO appointments (price, date, description, duration, patient,finished, type, doctor_id, medical_record_id, doctor_username,diagnosis_id,type2_id, recipe_id, info,pharmacy_id) VALUES (1,'2020-02-03T16:00', 'Pojava akni', 2, 'pacijent1',true, 'konsultacije', 2, 2, 'farmaceut1', 1,1, 1, "Uput za dermatologa",1);

INSERT INTO appointments (price, date, description, duration, patient,finished, type, doctor_id, medical_record_id, doctor_username,diagnosis_id,type2_id, recipe_id, info,pharmacy_id) VALUES (1,'2020-08-03T16:00', 'Preporuka kreme za suvu kozu', 2, 'pacijent1',true, 'konsultacije', 2, 2, 'farmaceut1', 1,2, 1, "Krema preporucena",1);

INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-26T10:00', 'Provera mladeza', 2, 'pacijent1', 'tip pregleda', false, 3, 5, 'f2',1,2);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-01-25T16:00', 'Redovna kontrola', 2, 'pacijent1', 'tip2', true, 6, 3, 'd2',2,1);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-23T16:00', 'Krema protiv crvenila', 2, 'p2', 'tip2', false, 2, 3, 'farmaceut1',1,1);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-24T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 6, 3, 'd2',2,1);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-25T16:00', 'Pastile za grlo', 2, 'p2', 'tip2', false, 2, 3, 'farmaceut1',1,1);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-19T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 6, 3, 'd2',2,1);
INSERT INTO appointments (price, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id, pharmacy_id) VALUES (1,'2021-02-20T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 6, 3, 'd2',2,1);

INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,1);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,2);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,3);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,4);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,5);

--brzi pregledi(Nemaju dodeljenog pacijenta, ni medical_record)
INSERT INTO appointments (price, date, description, duration, type, finished, doctor_id, doctor_username, type2_id,pharmacy_id) VALUES (200,'2021-03-03T12:00', 'Redovna kontrola', 2, 'tip7', false, 4, 'd1', 2,2);
INSERT INTO appointments (price, date, description, duration, type, finished, doctor_id, doctor_username, type2_id,pharmacy_id) VALUES (500,'2021-03-04T08:00', 'Redovna kontrola', 2, 'tip5', false, 4, 'd1', 2,1);

INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 1);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 2);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(3, 3);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(3, 4);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(6, 5);

INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (1,2);
INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (2,3);
INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (1,5);
INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (3,4);
INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (1,6);
INSERT INTO pharmacy_doctors(pharmacy_id, doctors_id) VALUES (1,7);

INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacists_id) VALUES (1,1);
INSERT INTO pharmacy_pharmacists(pharmacy_id, pharmacists_id) VALUES (1,8);

INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 5, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (1, 3, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (3, 5, 3);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 3, 3);

INSERT INTO patient_rated_pharmacy(ocena, patient_id, pharmacy_id) VALUES (3, 5, 1);

INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Brufen", 150, 100, 1);
INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Fervex", 550, 200,1);
INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Paracetamol", 550, 200,2);
INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Febricet", 550, 200,2);
INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Febricet", 550, 200,1);
INSERT INTO drug(name, price, quantity, pharmacy_id) VALUES ("Brufen", 259, 100, 2);

INSERT INTO recipe(authenticated, description, appointment_id) VALUES (false, "Dva puta na dan po dve tablete", 2);
INSERT INTO recipe(authenticated, description) VALUES (false, "Dnevno tri kesice");
INSERT INTO recipe(authenticated, description) VALUES (false, "Dnevno jedna kesica");
INSERT INTO recipe(authenticated, description) VALUES (true, "Dnevno dve kesice");

INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (1,1);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (2,2);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (3,2);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (4,2);

INSERT INTO appointment_type(name) VALUES ("Konsultacije kod farmaceuta");
INSERT INTO appointment_type(name) VALUES ("Dermatoloski pregled");

INSERT INTO pharmacy_pharmacy_administrator(pharmacy_id, pharmacy_administrator_id) VALUES (1,1);

INSERT INTO diagnosis(description, name) VALUE ("Temperatura", "Upala krajnika");
INSERT INTO diagnosis(description, name) VALUE ("Kasalj", "Korona");
INSERT INTO diagnosis(description, name) VALUE ("Temperatura", "Korona");
INSERT INTO diagnosis(description, name) VALUE ("Upala krajnika", "Bakterijska infekcija");

INSERT INTO price_list(price,appointment_type_id,pharmacy_id) values (1200,1,1);
INSERT INTO price_list(price,appointment_type_id,pharmacy_id) values (1000,2,1);
INSERT INTO price_list(price,appointment_type_id,pharmacy_id) values (3000,1,2);
INSERT INTO price_list(price,appointment_type_id,pharmacy_id) values (2000,2,2);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (1,1);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (2,2);


insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,1);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,2);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,3);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,4);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,5);

INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,1);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,2);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,3);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (2,4);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,5);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,6);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,7);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,8);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,9);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,10);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (2,11);
INSERT INTO pharmacy_appointments(pharmacy_id, appointments_id) VALUES (1,12);

insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,6);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,7);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,8);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,9);

insert into promotion (pharmacy_id,description, date) values(1,"Akcija ovog vikenda!!", '2021-02-10T10:00');
insert into promotion (pharmacy_id,description, date) values(2,"Bonus akcija!!", '2022-02-26T10:00');
insert into promotion (pharmacy_id,description, date) values(1,"Akcija, akcija i akcija!!", '2021-03-26T10:00');

insert into purchase_order (date,pharmacy_admin_id, name) values('2021-02-10T10:00',1, "Narudzbenica 1");
insert into purchase_order (date,pharmacy_admin_id, name) values('2021-03-10T10:00',1, "Narudzbenica 2");
insert into purchase_order (date,pharmacy_admin_id, name) values('2021-04-10T10:00',2, "Narudzbenica 3");

insert into purchase_order_drugs (purchase_order_id,drugs_id) values(1,1);
insert into purchase_order_drugs (purchase_order_id,drugs_id) values(1,2);
insert into purchase_order_drugs (purchase_order_id,drugs_id) values(2,3);



INSERT INTO holiday_request(finished, confirmed, date_end, date_start, medical_staff_id) values ( false, false, '2021-03-16', '2021-02-10', 2);