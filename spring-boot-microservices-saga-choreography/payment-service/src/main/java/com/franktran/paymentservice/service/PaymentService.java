package com.franktran.paymentservice.service;

import com.franktran.commondto.dto.OrderDto;
import com.franktran.commondto.dto.PaymentDto;
import com.franktran.commondto.event.order.OrderEvent;
import com.franktran.commondto.event.payment.PaymentEvent;
import com.franktran.commondto.event.payment.PaymentStatus;
import com.franktran.paymentservice.entity.UserTransaction;
import com.franktran.paymentservice.repository.UserBalanceRepository;
import com.franktran.paymentservice.repository.UserTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

  private final UserBalanceRepository userBalanceRepository;
  private final UserTransactionRepository userTransactionRepository;

  public PaymentService(UserBalanceRepository userBalanceRepository, UserTransactionRepository userTransactionRepository) {
    this.userBalanceRepository = userBalanceRepository;
    this.userTransactionRepository = userTransactionRepository;
  }

  @Transactional
  public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
    OrderDto order = orderEvent.getOrder();
    PaymentDto paymentDto = PaymentDto.of(order.getOrderId(), order.getUserId(), order.getPrice());
    return userBalanceRepository.findById(order.getUserId())
        .filter(userBalance -> userBalance.getBalance() >= order.getPrice())
        .map(userBalance -> {
          userBalance.setBalance(userBalance.getBalance() - order.getPrice());
          userTransactionRepository.save(UserTransaction.of(order.getOrderId(), order.getUserId(), order.getPrice()));
          return new PaymentEvent(paymentDto, PaymentStatus.RESERVED);
        })
        .orElse(new PaymentEvent(paymentDto, PaymentStatus.REJECTED));
  }

  @Transactional
  public void cancelOrderEvent(OrderEvent orderEvent) {
    userTransactionRepository.findById(orderEvent.getEventId())
        .ifPresent(userTransaction -> {
          userTransactionRepository.delete(userTransaction);
          userBalanceRepository.findById(userTransaction.getUserId())
              .ifPresent(userBalance -> userBalance.setBalance(userBalance.getBalance() + userTransaction.getAmount()));
        });
  }
}
