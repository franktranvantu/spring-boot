package com.franktran.orderservice.service;

import com.franktran.commondto.dto.OrderRequestDto;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.orderservice.eventhandler.OrderStatusPublisher;
import com.franktran.orderservice.repository.PurchaseOrderRepository;
import com.franktran.orderservice.entity.PurchaseOrder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderCommandService {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final ModelMapper modelMapper;
  private final OrderStatusPublisher orderStatusPublisher;
  private final Map<Integer, Integer> productPrice;

  public OrderCommandService(PurchaseOrderRepository purchaseOrderRepository,
                             ModelMapper modelMapper,
                             OrderStatusPublisher orderStatusPublisher,
                             Map<Integer, Integer> productPrice) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.modelMapper = modelMapper;
    this.orderStatusPublisher = orderStatusPublisher;
    this.productPrice = productPrice;
  }

  @Transactional
  public PurchaseOrder createOrder(OrderRequestDto orderRequest) {
    PurchaseOrder purchaseOrder = modelMapper.map(orderRequest, PurchaseOrder.class);
    purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
    purchaseOrder.setPrice(productPrice.get(orderRequest.getProductId()));
    purchaseOrderRepository.save(purchaseOrder);
    orderStatusPublisher.raiseOrderEvent(purchaseOrder, purchaseOrder.getOrderStatus());
    return purchaseOrder;
  }

}
