package com.franktran.jsp.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public String getStudents(Model model) {
    List<Student> students = studentService.getStudents();
    model.addAttribute("students", students);
    return "student-list";
  }

  @GetMapping("/create-student")
  public String showCreateStudent(@ModelAttribute("student") Student student) {
    return "save-student";
  }

  @PostMapping("/save-student")
  public String saveStudent(@ModelAttribute("student") Student student) {
    studentService.createStudent(student);
    return "redirect:/";
  }

  @GetMapping("/update-student/{id}")
  public String showUpdateStudent(@PathVariable int id, Model model) {
    Student student = studentService.getStudentById(id);
    model.addAttribute("student", student);
    return "save-student";
  }

}
