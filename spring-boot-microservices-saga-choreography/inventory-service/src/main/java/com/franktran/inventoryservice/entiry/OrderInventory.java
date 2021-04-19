package com.franktran.inventoryservice.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderInventory {

  @Id
  private int productId;
  private int availableInventory;
}
