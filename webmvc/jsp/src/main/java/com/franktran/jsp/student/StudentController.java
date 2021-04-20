package com.franktran.jsp.student;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
  public String showCreateStudent(@ModelAttribute("student") Student student, Model model) {
    model.addAttribute("action", "Create");
    return "save-student";
  }

  @PostMapping("/save-student")
  public String saveStudent(@ModelAttribute("student") Student student, Model model) {
    try {
      if (Objects.isNull(student.getId())) {
        model.addAttribute("action", "Create");
        studentService.createStudent(student);
      } else {
        model.addAttribute("action", "Update");
        studentService.updateStudent(student.getId(), student);
      }
      return "redirect:/";
    } catch (Exception e) {
      model.addAttribute("emailError", e.getMessage());
      return "save-student";
    }
  }

  @GetMapping("/update-student/{id}")
  public String showUpdateStudent(@PathVariable int id, Model model) {
    Student student = studentService.getStudentById(id);
    model.addAttribute("action", "Update");
    model.addAttribute("student", student);
    return "save-student";
  }

  @GetMapping("/delete-student/{id}")
  public String deleteStudent(@PathVariable int id) {
    studentService.deleteStudent(id);
    return "redirect:/";
  }

}
