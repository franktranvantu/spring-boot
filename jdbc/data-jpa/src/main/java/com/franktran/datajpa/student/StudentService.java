package com.franktran.datajpa.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public ResponseEntity<List<Student>> getStudents() {
    try {
      return ResponseEntity.ok(studentRepository.findAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<Student> getStudentById(long id) {
    try {
      return ResponseEntity.ok(studentRepository.findById(id).orElse(null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<String> createStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("email taken");
    }
    try {
      studentRepository.save(student);
      return ResponseEntity.ok("Student created successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @Transactional
  public ResponseEntity<String> updateStudent(long studentId, Student student) {
    Student existStudent = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException(String.format("Student with id %s not exists", studentId)));
    if (Objects.nonNull(student.getName()) && !Objects.equals(existStudent.getName(), student.getName())) {
      existStudent.setName(student.getName());
    }
    if (Objects.nonNull(student.getEmail()) && !Objects.equals(existStudent.getEmail(), student.getEmail())) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
      if (studentOptional.isPresent()) {
        throw new IllegalArgumentException("email taken");
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

  public ResponseEntity<String> deleteStudent(long studentId) {
    boolean existsById = studentRepository.existsById(studentId);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", studentId));
    }
    try {
      studentRepository.deleteById(studentId);
      return ResponseEntity.ok("Student deleted successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
