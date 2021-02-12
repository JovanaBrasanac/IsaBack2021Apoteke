package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Pharmacist;
import com.repository.PharmacistRepository;

@Service
public class PharmacistService {

    @Autowired
    private PharmacistRepository nr;

    public Pharmacist findByUsername(String username) {
        return (Pharmacist) nr.findByUsername(username);
    }

    public Pharmacist save(Pharmacist patient) {
        return nr.save(patient);
    }
}
