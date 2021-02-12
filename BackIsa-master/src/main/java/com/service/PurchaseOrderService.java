package com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.PurchaseOrder;
import com.repository.PurchaseOrderRepository;

import java.util.List;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository por;

	public List<PurchaseOrder> findAll() {
		return por.findAll();
	}

	public PurchaseOrder findById(Long id) {
		return por.findById(id).get();
	}

	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		return por.save(purchaseOrder);
	}

	public void delete(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub
		por.delete(purchaseOrder);
		
	}

}
