package com.franktran.jsp.student;

import org.springframework.dao.DataIntegrityViolationException;
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

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student getStudentById(long id) {
    return studentRepository.findById(id).orElse(null);
  }

  public Student createStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }
    return studentRepository.save(student);
  }

  public Student updateStudent(long studentId, Student student) {
    Student existStudent = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException(String.format("Student with id %s not exists", studentId)));
    if (Objects.nonNull(student.getName()) && !Objects.equals(existStudent.getName(), student.getName())) {
      existStudent.setName(student.getName());
    }
    if (Objects.nonNull(student.getEmail()) && !Objects.equals(existStudent.getEmail(), student.getEmail())) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
      if (studentOptional.isPresent()) {
        throw new IllegalArgumentException("Email already exists");
      }
      existStudent.setEmail(student.getEmail());
    }
    if (Objects.nonNull(student.getDob()) && !Objects.equals(existStudent.getDob(), student.getDob())) {
      existStudent.setDob(student.getDob());
    }

    return studentRepository.save(existStudent);
  }

  public void deleteStudent(long studentId) {
    Optional<Student> student = studentRepository.findById(studentId);
    if (!student.isPresent()) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", studentId));
    }
    try {
      studentRepository.deleteById(studentId);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Student %s is being used by others!", student.get().getName()));
    }
  }

}
