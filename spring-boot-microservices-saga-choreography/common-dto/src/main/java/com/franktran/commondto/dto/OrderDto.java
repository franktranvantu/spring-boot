package com.franktran.commondto.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDto {

  private UUID orderId;
  private Integer userId;
  private Integer productId;
  private Integer price;
}
