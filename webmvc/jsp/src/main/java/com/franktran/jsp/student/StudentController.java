package com.franktran.jsp.student;

import com.franktran.jsp.dto.ResultDto;
import com.franktran.jsp.dto.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public String index(Model model) {
    List<Student> students = studentService.getAllStudents();
    model.addAttribute("students", students);
    return "student/student-list";
  }

  @GetMapping("/create-student")
  public String showCreateStudent(@ModelAttribute("student") Student student, Model model) {
    model.addAttribute("action", "Create");
    return "student/save-student";
  }

  @PostMapping("/save-student")
  public String saveStudent(@ModelAttribute("student") Student student, Model model) {
    ResultDto result = new ResultDto();
    try {
      if (Objects.isNull(student.getId())) {
        model.addAttribute("action", "Create");
        studentService.createStudent(student);
        result.setMessage("Created student successful!");
        model.addAttribute("result", result);
      } else {
        model.addAttribute("action", "Update");
        studentService.updateStudent(student.getId(), student);
        result.setMessage("Updated student successful!");
        model.addAttribute("result", result);
      }
      result.setStatus(ResultStatus.SUCCESS);
      model.addAttribute("result", result);
      model.addAttribute("students", studentService.getAllStudents());
      return "student/student-list";
    } catch (Exception e) {
      result.setStatus(ResultStatus.FAIL);
      result.setMessage(e.getMessage());
      model.addAttribute("result", result);
      return "student/save-student";
    }
  }

  @GetMapping("/update-student/{id}")
  public String showUpdateStudent(@PathVariable int id, Model model) {
    Student student = studentService.getStudentById(id);
    model.addAttribute("action", "Update");
    model.addAttribute("student", student);
    return "student/save-student";
  }

  @GetMapping("/delete-student/{id}")
  public String deleteStudent(@PathVariable int id, Model model) {
    ResultDto result = new ResultDto();
    try {
      studentService.deleteStudent(id);
      result.setStatus(ResultStatus.SUCCESS);
      result.setMessage("Deleted student successful!");
      model.addAttribute("result", result);
    } catch (Exception e) {
      result.setStatus(ResultStatus.FAIL);
      result.setMessage(e.getMessage());
      model.addAttribute("result", result);
    }
    List<Student> students = studentService.getAllStudents();
    model.addAttribute("students", students);
    return "student/student-list";
  }

}
