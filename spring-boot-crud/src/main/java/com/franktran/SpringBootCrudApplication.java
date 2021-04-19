package com.franktran;

import com.franktran.student.Student;
import com.franktran.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootCrudApplication {

  private final StudentRepository studentRepository;

  public SpringBootCrudApplication(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCrudApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      List<Student> students = Arrays.asList(
        new Student("Frank", "frank@gmail.com", LocalDate.of(1992, Month.SEPTEMBER, 12)),
        new Student("Henry", "henry@gmail.com", LocalDate.of(1984, Month.JANUARY, 21)),
        new Student("Bean", "bean@gmail.com", LocalDate.of(1984, Month.APRIL, 9))
      );
      studentRepository.saveAll(students);
    };
  }
}
