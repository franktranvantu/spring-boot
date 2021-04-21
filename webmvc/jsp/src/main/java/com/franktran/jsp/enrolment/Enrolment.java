package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.Course;
import com.franktran.jsp.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinTable(
      name = "enrolment_student",
      joinColumns = {@JoinColumn(name = "enrolment_id")},
      inverseJoinColumns = {@JoinColumn(name = "student_id")}
  )
  private Student student;

  @OneToOne
  @JoinTable(
      name = "enrolment_course",
      joinColumns = {@JoinColumn(name = "enrolment_id")},
      inverseJoinColumns = {@JoinColumn(name = "course_id")}
  )
  private Course course;

  private String semester;

  public Enrolment(Course course, Student student, String semester) {
    this.student = student;
    this.course = course;
    this.semester = semester;
  }
}
