package com.franktran.jsp.course;

import com.franktran.jsp.enrolment.Enrolment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Name is mandatory")
  private String name;
  @OneToMany(mappedBy = "course")
  private Set<Enrolment> enrolments = new HashSet<>();

  public Course(String name) {
    this.name = name;
  }

}
