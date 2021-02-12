package com.repository;

import com.model.Drug;
import com.model.DrugReservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {

	DrugReservation save(DrugReservation drugReservation);
}
