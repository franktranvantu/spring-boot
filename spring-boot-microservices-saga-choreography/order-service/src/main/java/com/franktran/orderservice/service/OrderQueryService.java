package com.franktran.orderservice.service;

import com.franktran.orderservice.entity.PurchaseOrder;
import com.franktran.orderservice.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {

  private final PurchaseOrderRepository purchaseOrderRepository;

  public OrderQueryService(PurchaseOrderRepository purchaseOrderRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
  }

  public List<PurchaseOrder> getAll() {
    return purchaseOrderRepository.findAll();
  }
}
