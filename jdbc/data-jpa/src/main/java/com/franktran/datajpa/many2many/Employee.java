package com.franktran.datajpa.many2many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "employee_project",
      joinColumns = {@JoinColumn(name = "employee_id")},
      inverseJoinColumns = {@JoinColumn(name = "project_id")}
  )
  private Set<Project> projects = new HashSet<>();
}
