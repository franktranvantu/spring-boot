package com.franktran.datajpa.service;

import com.franktran.datajpa.config.DateRange;
import com.franktran.datajpa.config.SearchCriteria;
import com.franktran.datajpa.config.StudentSpecification;
import com.franktran.datajpa.entity.Student;
import com.franktran.datajpa.repository.StudentRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public ResponseEntity<List<Student>> getStudents(String name, String email, DateRange dobRange) {
    try {
      StudentSpecification nameSpec = new StudentSpecification(new SearchCriteria("name", name));
      StudentSpecification emailSpec = new StudentSpecification(new SearchCriteria("email", email));
      StudentSpecification dobSpec = new StudentSpecification(new SearchCriteria("dob", dobRange));
      List<Student> students = studentRepository.findAll(Specification.where(nameSpec).and(emailSpec).and(dobSpec));
      return ResponseEntity.ok(students);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<Student> getStudentById(Long id) {
    try {
      return ResponseEntity.ok(studentRepository.findById(id).orElse(null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<String> createStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("Email taken");
    }
    try {
      studentRepository.save(student);
      return ResponseEntity.ok("Student created successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  public ResponseEntity<String> updateStudent(Long id, Student student) {
    Student existStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Student with id %s not exists", id)));
    if (Objects.nonNull(student.getName()) && !Objects.equals(existStudent.getName(), student.getName())) {
      existStudent.setName(student.getName());
    }
    if (Objects.nonNull(student.getEmail()) && !Objects.equals(existStudent.getEmail(), student.getEmail())) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
      if (studentOptional.isPresent()) {
        throw new IllegalArgumentException("Email taken");
      }
      existStudent.setEmail(student.getEmail());
    }
    try {
      studentRepository.save(existStudent);
      return ResponseEntity.ok("Student updated successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  public ResponseEntity<String> deleteStudent(Long id) {
    boolean existsById = studentRepository.existsById(id);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", id));
    }
    try {
      studentRepository.deleteById(id);
      return ResponseEntity.ok("Student deleted successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
