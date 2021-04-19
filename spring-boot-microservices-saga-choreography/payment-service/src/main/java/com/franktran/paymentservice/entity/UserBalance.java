package com.franktran.paymentservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserBalance {

  @Id
  private int userId;
  private int balance;
}
