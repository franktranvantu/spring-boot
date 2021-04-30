package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.Course;
import com.franktran.jsp.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinTable(
      name = "enrolment_student",
      joinColumns = {@JoinColumn(name = "enrolment_id")},
      inverseJoinColumns = {@JoinColumn(name = "student_id")}
  )
  @NotNull(message = "Student is mandatory")
  private Student student;

  @ManyToOne
  @JoinTable(
      name = "enrolment_course",
      joinColumns = {@JoinColumn(name = "enrolment_id")},
      inverseJoinColumns = {@JoinColumn(name = "course_id")}
  )
  @NotNull(message = "Course is mandatory")
  private Course course;

  @NotBlank(message = "Semester is mandatory")
  private String semester;

  public Enrolment(Course course, Student student, String semester) {
    this.student = student;
    this.course = course;
    this.semester = semester;
  }
}
