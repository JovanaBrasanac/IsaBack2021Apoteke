package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	Promotion save(Promotion promotion);
}
