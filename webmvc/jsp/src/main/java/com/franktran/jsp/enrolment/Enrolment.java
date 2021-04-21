package com.franktran.jsp.enrolment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long studentId;
  private Long courseId;
  private String semester;

  public Enrolment(Long studentId, Long courseId, String semester) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.semester = semester;
  }
}
