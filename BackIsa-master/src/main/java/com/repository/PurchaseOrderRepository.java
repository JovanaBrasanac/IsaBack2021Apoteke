package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

	PurchaseOrder save(PurchaseOrder purchaseOrder);
}
