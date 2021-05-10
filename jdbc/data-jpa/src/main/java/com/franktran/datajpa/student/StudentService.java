package com.franktran.datajpa.student;

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
    return ResponseEntity.ok(studentRepository.findAll());
  }

  public ResponseEntity<Student> getStudentById(long id) {
    return ResponseEntity.ok(studentRepository.findById(id).orElse(null));
  }

  public ResponseEntity<Student> createStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("email taken");
    }
    return ResponseEntity.ok(studentRepository.save(student));
  }

  @Transactional
  public ResponseEntity<Student> updateStudent(long studentId, Student student) {
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

    return ResponseEntity.ok(studentRepository.save(existStudent));
  }

  public void deleteStudent(long studentId) {
    boolean existsById = studentRepository.existsById(studentId);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", studentId));
    }
    studentRepository.deleteById(studentId);
  }

}
