package com.franktran.jsp.student;

import com.franktran.jsp.enrolment.Enrolment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Email is mandatory")
  @Email
  private String email;
  private LocalDate dob;
  @OneToMany(mappedBy = "student")
  private Set<Enrolment> enrolments = new HashSet<>();

  public Student(String name, String email, LocalDate dob) {
    this.name = name;
    this.email = email;
    this.dob = dob;
  }
}
