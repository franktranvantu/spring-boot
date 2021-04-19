package com.franktran.commondto.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDto {

  private UUID orderId;
  private Integer userId;
  private Integer productId;
  private Integer amount;

}
