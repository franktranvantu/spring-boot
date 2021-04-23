package com.franktran.exeptionhandling.student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  @GetMapping("/{id}")
  public Student getStudentById(@PathVariable long id) {
    return studentService.getStudentById(id);
  }

  @PostMapping
  public void createStudent(@RequestBody Student student) {
    studentService.createStudent(student);
  }

  @PutMapping("{studentId}")
  public void updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
    studentService.updateStudent(studentId, student);
  }

  @DeleteMapping("{studentId}")
  public void deleteStudent(@PathVariable Long studentId) {
    studentService.deleteStudent(studentId);
  }

}
