package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.CourseService;
import com.franktran.jsp.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(3)
public class EnrolmentBootStrap implements CommandLineRunner {

  private final EnrolmentRepository enrolmentRepository;
  private final CourseService courseService;
  private final StudentService studentService;

  public EnrolmentBootStrap(EnrolmentRepository enrolmentRepository, CourseService courseService, StudentService studentService) {
    this.enrolmentRepository = enrolmentRepository;
    this.courseService = courseService;
    this.studentService = studentService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Enrolment> enrolments = Stream.of(
        new Enrolment(courseService.getCourseById(1), studentService.getStudentById(1), "2021A"),
        new Enrolment(courseService.getCourseById(2), studentService.getStudentById(2), "2021A"),
        new Enrolment(courseService.getCourseById(3), studentService.getStudentById(3), "2021A")
    ).collect(Collectors.toList());

    enrolmentRepository.saveAll(enrolments);
  }
}
