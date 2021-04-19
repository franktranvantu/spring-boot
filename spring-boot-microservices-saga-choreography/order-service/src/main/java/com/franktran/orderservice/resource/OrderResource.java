package com.franktran.orderservice.resource;

import com.franktran.commondto.dto.OrderRequestDto;
import com.franktran.orderservice.service.OrderCommandService;
import com.franktran.orderservice.entity.PurchaseOrder;
import com.franktran.orderservice.service.OrderQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderResource {

  private final OrderQueryService orderQueryService;
  private final OrderCommandService orderCommandService;

  public OrderResource(OrderQueryService orderQueryService, OrderCommandService orderCommandService) {
    this.orderQueryService = orderQueryService;
    this.orderCommandService = orderCommandService;
  }

  @GetMapping
  public List<PurchaseOrder> getOrders() {
    return orderQueryService.getAll();
  }

  @PostMapping
  public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequest) {
    orderRequest.setOrderId(UUID.randomUUID());
    return orderCommandService.createOrder(orderRequest);
  }
}
