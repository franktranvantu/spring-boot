package com.franktran.orderservice.eventhandler;

import com.franktran.commondto.dto.OrderDto;
import com.franktran.commondto.event.order.OrderEvent;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.orderservice.entity.PurchaseOrder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

  private final Sinks.Many<OrderEvent> orderSink;
  private final ModelMapper modelMapper;

  public OrderStatusPublisher(Sinks.Many<OrderEvent> orderSink, ModelMapper modelMapper) {
    this.orderSink = orderSink;
    this.modelMapper = modelMapper;
  }

  public void raiseOrderEvent(PurchaseOrder purchaseOrder, OrderStatus status) {
    OrderDto orderDto = modelMapper.map(purchaseOrder, OrderDto.class);
    OrderEvent orderEvent = new OrderEvent(orderDto, status);
    orderSink.tryEmitNext(orderEvent);
  }
}
