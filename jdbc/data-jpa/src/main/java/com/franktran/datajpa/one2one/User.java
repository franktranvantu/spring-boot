package com.franktran.datajpa.one2one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "frank")
//  @JoinTable(
//      name = "user_address",
//      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//      inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "frank")}
//  )
  private Address address;

}
