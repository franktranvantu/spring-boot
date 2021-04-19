package com.franktran.inventoryservice.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderInventoryConsumption {

  @Id
  private UUID orderId;
  private int productId;
  private int quantityConsumed;
}
