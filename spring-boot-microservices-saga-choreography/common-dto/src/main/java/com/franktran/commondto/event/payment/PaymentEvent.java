package com.franktran.commondto.event.payment;

import com.franktran.commondto.dto.PaymentDto;
import com.franktran.commondto.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent extends Event {

  private PaymentDto payment;
  private PaymentStatus status;

}
