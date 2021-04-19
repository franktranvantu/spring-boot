package com.franktran.paymentservice.eventhandler;

import com.franktran.commondto.event.order.OrderEvent;
import com.franktran.commondto.event.order.OrderStatus;
import com.franktran.commondto.event.payment.PaymentEvent;
import com.franktran.paymentservice.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentEventHandler {

  private final PaymentService paymentService;

  public PaymentEventHandler(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @Bean
  public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
    return flux -> flux.flatMap(this::processPayment);
  }

  private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
    if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
      return Mono.fromSupplier(() -> paymentService.newOrderEvent(orderEvent));
    }
    return Mono.fromRunnable(() -> paymentService.cancelOrderEvent(orderEvent));
  }
}
