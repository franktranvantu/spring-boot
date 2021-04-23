package com.franktran.mustache.student;

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

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public Student getStudentById(long id) {
    return studentRepository.findById(id).orElse(null);
  }

  public Student createStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("email taken");
    }
    return studentRepository.save(student);
  }

  @Transactional
  public Student updateStudent(long studentId, Student student) {
    Student existStudent = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException(String.format("Student with id %s not exists", studentId)));
    if (Objects.nonNull(student.getName()) && !Objects.equals(student.getName(), student.getName())) {
      student.setName(student.getName());
    }
    if (Objects.nonNull(student.getEmail()) && !Objects.equals(student.getEmail(), student.getEmail())) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
      if (studentOptional.isPresent()) {
        throw new IllegalArgumentException("email taken");
      }
      student.setEmail(student.getEmail());
    }

    return studentRepository.save(student);
  }

  public void deleteStudent(long studentId) {
    boolean existsById = studentRepository.existsById(studentId);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", studentId));
    }
    studentRepository.deleteById(studentId);
  }

}
