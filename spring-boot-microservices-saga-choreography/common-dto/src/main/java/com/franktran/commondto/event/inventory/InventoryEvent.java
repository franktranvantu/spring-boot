package com.franktran.commondto.event.inventory;

import com.franktran.commondto.dto.InventoryDto;
import com.franktran.commondto.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent extends Event {

  private InventoryDto inventory;
  private InventoryStatus status;

}
