package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Appointment;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Optional<Appointment> findById(Long aLong);

	List<Appointment> findAll();

	List<Appointment> findByFinished(Boolean finished);
	
	void delete(Appointment app);
	
	Appointment findByDate(String date);
	
	void deleteById(Long id);
	
	@Override
	Appointment saveAndFlush(Appointment entity);
	

}
