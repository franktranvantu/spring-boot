package com.franktran.orderservice.eventhandler;

import com.franktran.commondto.event.inventory.InventoryStatus;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.commondto.event.payment.PaymentStatus;
import com.franktran.orderservice.entity.PurchaseOrder;
import org.springframework.stereotype.Service;
import com.franktran.orderservice.repository.PurchaseOrderRepository;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class OrderStatusUpdateEventHandler {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final OrderStatusPublisher orderStatusPublisher;

  public OrderStatusUpdateEventHandler(PurchaseOrderRepository purchaseOrderRepository, OrderStatusPublisher orderStatusPublisher) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.orderStatusPublisher = orderStatusPublisher;
  }

  public void updateOrder(UUID orderId, Consumer<PurchaseOrder> consumer) {
    purchaseOrderRepository.findById(orderId)
        .ifPresent(consumer.andThen(this::updateOrder));
  }

  private void updateOrder(PurchaseOrder order) {
    if (Objects.isNull(order.getPaymentStatus())
        || Objects.isNull(order.getInventoryStatus())) {
      return;
    }

    boolean isComplete = PaymentStatus.RESERVED.equals(order.getPaymentStatus())
        && InventoryStatus.RESERVED.equals(order.getInventoryStatus());
    OrderStatus orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
    order.setOrderStatus(orderStatus);

    if (!isComplete) {
      orderStatusPublisher.raiseOrderEvent(order, orderStatus);
    }
  }
}
