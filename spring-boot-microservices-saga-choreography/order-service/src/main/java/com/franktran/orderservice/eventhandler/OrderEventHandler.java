package com.franktran.orderservice.eventhandler;

import com.franktran.commondto.event.inventory.InventoryEvent;
import com.franktran.commondto.event.payment.PaymentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderEventHandler {

  private final OrderStatusUpdateEventHandler orderEventHandler;

  public OrderEventHandler(OrderStatusUpdateEventHandler orderEventHandler) {
    this.orderEventHandler = orderEventHandler;
  }

  @Bean
  public Consumer<PaymentEvent> paymentEventConsumer() {
    return paymentEvent -> {
      orderEventHandler.updateOrder(
          paymentEvent.getPayment().getOrderId(),
          po -> po.setPaymentStatus(paymentEvent.getStatus())
      );
    };
  }

  @Bean
  public Consumer<InventoryEvent> inventoryEventConsumer() {
    return inventoryEvent -> {
      orderEventHandler.updateOrder(
          inventoryEvent.getInventory().getOrderId(),
          po -> po.setInventoryStatus(inventoryEvent.getStatus())
      );
    };
  }
}
