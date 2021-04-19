package com.franktran.inventoryservice.config;

import com.franktran.commondto.event.inventory.InventoryEvent;
import com.franktran.commondto.event.order.OrderEvent;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.inventoryservice.service.InventoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class InventoryConfig {

  private final InventoryService inventoryService;

  public InventoryConfig(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @Bean
  public Function<Flux<OrderEvent>, Flux<InventoryEvent>> inventoryProcessor() {
    return flux -> flux.flatMap(this::processInventory);
  }

  private Mono<InventoryEvent> processInventory(OrderEvent orderEvent) {
    if (orderEvent.getOrderStatus().equals(OrderStatus.ORDER_CREATED)) {
      return Mono.fromSupplier(() -> inventoryService.newOrderInventory(orderEvent));
    }
    return Mono.fromRunnable(() -> inventoryService.cancelOrderInventory(orderEvent));
  }
}
