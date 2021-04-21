package com.franktran.datajpa.one2many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinTable(
      name = "items_cart",
      joinColumns = {@JoinColumn(name = "items_id")},
      inverseJoinColumns = {@JoinColumn(name = "cart_id")}
  )
  private Cart cart;

}
