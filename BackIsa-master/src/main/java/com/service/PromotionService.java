package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Promotion;
import com.repository.PromotionRepository;

import java.util.List;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository pr;

	public List<Promotion> findAll() {
		return pr.findAll();
	}

	public Promotion findById(Long id) {
		return pr.findById(id).get();
	}

	public Promotion save(Promotion promotion) {
		return pr.save(promotion);
	}

	public void delete(Promotion promotion) {
		// TODO Auto-generated method stub
		pr.delete(promotion);
		
	}

}
