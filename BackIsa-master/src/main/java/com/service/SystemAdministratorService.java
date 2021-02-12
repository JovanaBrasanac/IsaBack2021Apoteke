package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.model.SystemAdministrator;
import com.repository.SystemAdministratorRepository;

@Service
public class SystemAdministratorService implements UserDetailsService {

	@Autowired
	private SystemAdministratorRepository ccar;

	public SystemAdministrator findByUsername(String username) {

		return ccar.findByUsername(username);
	}

	public SystemAdministrator save(SystemAdministrator cca) {

		return ccar.save(cca);
	}

	//Implementacija metode interfejsa
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemAdministrator admin = ccar.findByUsername(username);

		if (admin == null) {
			throw new UsernameNotFoundException(
					String.format("Ne postoji korisnik sa takvim korisnickim imenom '%s'.", username));
		} else {
			return admin;
		}
	}
}
