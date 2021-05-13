package com.franktran.datajpa.student;

import com.franktran.datajpa.DateRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String email,
                                                   @RequestParam(required = false) DateRange dobRange) {
    return studentService.getStudents(name, email, dobRange);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    return studentService.getStudentById(id);
  }

  @PostMapping
  public ResponseEntity<String> createStudent(@RequestBody Student student) {
    return studentService.createStudent(student);
  }

  @PutMapping("{id}")
  public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
    return studentService.updateStudent(id, student);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    return studentService.deleteStudent(id);
  }

}
