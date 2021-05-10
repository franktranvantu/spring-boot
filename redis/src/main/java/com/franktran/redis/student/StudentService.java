package com.franktran.redis.student;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

  private final RedisTemplate redisTemplate;
  private static final String KEY = "STUDENT";

  public StudentService(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public ResponseEntity<List<Student>> getStudents() {
    try {
      List<Student> students = redisTemplate.opsForHash().values(KEY);
      return ResponseEntity.ok(students);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<Student> getStudentById(long id) {
    try {
      Student student = (Student) redisTemplate.opsForHash().get(KEY, id);
      return ResponseEntity.ok(student);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  public ResponseEntity<String> createStudent(Student student) {
    List<Student> students = redisTemplate.opsForHash().values(KEY);
    Optional<Student> studentOptional = students.stream().filter(s -> s.getEmail().equalsIgnoreCase(student.getEmail())).findFirst();
    if (studentOptional.isPresent()) {
      throw new IllegalArgumentException("Email taken");
    }
    try {
      redisTemplate.opsForHash().put(KEY, student.getId().toString(), student);
      return ResponseEntity.ok("Student created successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @Transactional
  public ResponseEntity<String> updateStudent(long studentId, Student student) {
    Student existStudent = (Student) redisTemplate.opsForHash().get(KEY, studentId);
    if (Objects.nonNull(student.getName()) && !Objects.equals(existStudent.getName(), student.getName())) {
      existStudent.setName(student.getName());
    }
    if (Objects.nonNull(student.getEmail()) && !Objects.equals(existStudent.getEmail(), student.getEmail())) {
        List<Student> students = redisTemplate.opsForHash().values(KEY);
        Optional<Student> studentOptional = students.stream().filter(s -> s.getEmail().equalsIgnoreCase(student.getEmail())).findFirst();      if (studentOptional.isPresent()) {
        throw new IllegalArgumentException("Email taken");
      }
      existStudent.setEmail(student.getEmail());
    }
    try {
      redisTemplate.opsForHash().put(KEY, studentId, student);
      return ResponseEntity.ok("Student updated successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  public ResponseEntity<String> deleteStudent(long studentId) {
    Student student = (Student) redisTemplate.opsForHash().get(KEY, studentId);
    if (Objects.isNull(student)) {
      throw new IllegalArgumentException(String.format("Student with id %s not exists", studentId));
    }
    try {
      redisTemplate.opsForHash().delete(KEY, student);
      return ResponseEntity.ok("Student deleted successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
