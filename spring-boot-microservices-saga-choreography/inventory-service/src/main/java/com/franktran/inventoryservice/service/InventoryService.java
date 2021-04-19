package com.franktran.inventoryservice.service;

import com.franktran.commondto.dto.InventoryDto;
import com.franktran.commondto.event.inventory.InventoryEvent;
import com.franktran.commondto.event.inventory.InventoryStatus;
import com.franktran.commondto.event.order.OrderEvent;
import com.franktran.inventoryservice.entiry.OrderInventoryConsumption;
import com.franktran.inventoryservice.repository.OrderInventoryConsumptionRepository;
import com.franktran.inventoryservice.repository.OrderInventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

  private final OrderInventoryRepository orderInventoryRepository;
  private final OrderInventoryConsumptionRepository consumptionRepository;

  public InventoryService(OrderInventoryRepository orderInventoryRepository, OrderInventoryConsumptionRepository consumptionRepository) {
    this.orderInventoryRepository = orderInventoryRepository;
    this.consumptionRepository = consumptionRepository;
  }

  @Transactional
  public InventoryEvent newOrderInventory(OrderEvent orderEvent) {
    InventoryDto inventoryDto = InventoryDto.of(orderEvent.getOrder().getOrderId(), orderEvent.getOrder().getProductId());
    return orderInventoryRepository.findById(orderEvent.getOrder().getProductId())
        .filter(inventory -> inventory.getAvailableInventory() > 0)
        .map(inventory -> {
          inventory.setAvailableInventory(inventory.getAvailableInventory() - 1);
          consumptionRepository.save(OrderInventoryConsumption.of(orderEvent.getOrder().getOrderId(), orderEvent.getOrder().getProductId(), 1));
          return new InventoryEvent(inventoryDto, InventoryStatus.RESERVED);
        })
        .orElse(new InventoryEvent(inventoryDto, InventoryStatus.REJECTED));
  }

  @Transactional
  public void cancelOrderInventory(OrderEvent orderEvent) {
    consumptionRepository.findById(orderEvent.getOrder().getOrderId())
        .ifPresent(inventoryConsumption -> {
          orderInventoryRepository.findById(inventoryConsumption.getProductId())
              .ifPresent(inventory -> inventory.setAvailableInventory(inventory.getAvailableInventory() + inventoryConsumption.getQuantityConsumed()));
          consumptionRepository.delete(inventoryConsumption);
        });
  }
}
