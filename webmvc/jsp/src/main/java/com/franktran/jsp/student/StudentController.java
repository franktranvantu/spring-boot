package com.franktran.jsp.student;

import com.franktran.jsp.course.Course;
import com.franktran.jsp.course.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;
  private final CourseService courseService;

  public StudentController(StudentService studentService, CourseService courseService) {
    this.studentService = studentService;
    this.courseService = courseService;
  }

  @GetMapping
  public String index(Model model) {
    List<Student> students = studentService.getStudents();
    model.addAttribute("students", students);
    return "student/student-list";
  }

  @GetMapping("/create-student")
  public String showCreateStudent(@ModelAttribute("student") Student student, Model model) {
    List<Course> courses = courseService.getAllCourses();
    model.addAttribute("action", "Create");
    model.addAttribute("courses", courses);
    return "student/save-student";
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
      return "redirect:/student";
    } catch (Exception e) {
      model.addAttribute("emailError", e.getMessage());
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
  public String deleteStudent(@PathVariable int id) {
    studentService.deleteStudent(id);
    return "redirect:/student";
  }

}
