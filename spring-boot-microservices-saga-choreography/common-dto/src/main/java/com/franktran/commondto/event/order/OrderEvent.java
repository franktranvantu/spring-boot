package com.franktran.commondto.event.order;

import com.franktran.commondto.dto.OrderDto;
import com.franktran.commondto.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent extends Event {

  private OrderDto order;
  private OrderStatus orderStatus;

}
