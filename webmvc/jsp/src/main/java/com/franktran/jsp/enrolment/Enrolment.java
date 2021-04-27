package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.Course;
import com.franktran.jsp.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
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
