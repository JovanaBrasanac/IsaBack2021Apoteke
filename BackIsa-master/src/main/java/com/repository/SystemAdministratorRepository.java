package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.SystemAdministrator;
import java.lang.String;

public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {

	Page<SystemAdministrator> findAll(Pageable page);

	SystemAdministrator findByUsername(String username);

}
