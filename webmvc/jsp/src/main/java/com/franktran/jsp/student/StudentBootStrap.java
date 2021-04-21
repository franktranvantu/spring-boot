package com.franktran.jsp.student;

import com.franktran.jsp.course.CourseService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(2)
public class StudentBootStrap implements CommandLineRunner {

  private final StudentRepository studentRepository;
  private final CourseService courseService;

  public StudentBootStrap(StudentRepository studentRepository, CourseService courseService) {
    this.studentRepository = studentRepository;
    this.courseService = courseService;
  }

  @Override
  public void run(String... args) throws Exception {
    Faker faker = new Faker();
    List<Student> students = Stream.of(
        new Student(
            faker.name().name(),
            "faker1@gmail.com",
            faker.date().birthday(10, 50).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            Arrays.asList(courseService.getCourseById(1), courseService.getCourseById(2))
        ),
        new Student(
            faker.name().name(),
            "faker2@gmail.com",
            faker.date().birthday(10, 50).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            Arrays.asList(courseService.getCourseById(2))
        ),
        new Student(
            faker.name().name(),
            "faker3@gmail.com",
            faker.date().birthday(10, 50).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            courseService.getAllCourses()
        )
    ).collect(Collectors.toList());

    studentRepository.saveAll(students);
  }
}
