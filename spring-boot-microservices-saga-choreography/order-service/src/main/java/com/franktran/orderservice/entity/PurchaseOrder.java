package com.franktran.orderservice.entity;

import com.franktran.commondto.event.inventory.InventoryStatus;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.commondto.event.payment.PaymentStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class PurchaseOrder {

  @Id
  private UUID id;
  private Integer userId;
  private Integer productId;
  private Integer price;
  private OrderStatus orderStatus;
  private PaymentStatus paymentStatus;
  private InventoryStatus inventoryStatus;

}
