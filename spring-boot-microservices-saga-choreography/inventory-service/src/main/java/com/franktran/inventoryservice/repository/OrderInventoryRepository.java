package com.franktran.inventoryservice.repository;

import com.franktran.inventoryservice.entiry.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {
}
