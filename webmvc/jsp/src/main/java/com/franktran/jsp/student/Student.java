package com.franktran.jsp.student;

import com.franktran.jsp.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private LocalDate dob;

  public Student(String name, String email, LocalDate dob) {
    this.name = name;
    this.email = email;
    this.dob = dob;
  }
}
