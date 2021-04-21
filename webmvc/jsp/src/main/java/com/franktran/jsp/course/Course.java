package com.franktran.jsp.course;

import com.franktran.jsp.enrolment.Enrolment;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToOne(mappedBy = "course")
  private Enrolment enrolment;

  public Course(String name) {
    this.name = name;
  }

}
