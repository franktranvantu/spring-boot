package com.franktran.jsp.student;

import com.franktran.jsp.config.security.UserRole;
import com.franktran.jsp.dto.ResultDto;
import com.franktran.jsp.dto.ResultStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.franktran.jsp.config.security.UserRole.ADMIN;
import static com.franktran.jsp.config.security.UserRole.STUDENT;

@Controller
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENROLMENT', 'ROLE_COURSE', 'ROLE_STUDENT')")
  public String index(Model model, Authentication authentication) {
    UserRole[] editableRoles = new UserRole[] {ADMIN, STUDENT};
    List<Student> students = studentService.getAllStudents();
    model.addAttribute("students", students);
    model.addAttribute("isEditable", UserRole.isEditable(editableRoles, authentication.getAuthorities()));
    return "student/student-list";
  }

  @GetMapping("/create-student")
  @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'STUDENT:WRITE')")
  public String showCreateStudent(@ModelAttribute("student") Student student, Model model) {
    model.addAttribute("action", "Create");
    return "student/save-student";
  }

  @PostMapping("/save-student")
  @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'STUDENT:WRITE')")
  public String saveStudent(@ModelAttribute("student") @Valid Student student,
                            BindingResult bindingResult,
                            RedirectAttributes ra,
                            Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("action", Objects.isNull(student.getId()) ? "Create" : "Update");
      return "student/save-student";
    }
    ResultDto result = new ResultDto();
    try {
      if (Objects.isNull(student.getId())) {
        model.addAttribute("action", "Create");
        studentService.createStudent(student);
        result.setMessage("Created student successful!");
      } else {
        model.addAttribute("action", "Update");
        studentService.updateStudent(student.getId(), student);
        result.setMessage("Updated student successful!");
      }
      result.setStatus(ResultStatus.SUCCESS);
      ra.addFlashAttribute("result", result);
      return "redirect:/student";
    } catch (Exception e) {
      result.setStatus(ResultStatus.FAIL);
      result.setMessage(e.getMessage());
      model.addAttribute("result", result);
      return "student/save-student";
    }
  }

  @PostMapping("/update-student")
  @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'STUDENT:WRITE')")
  public String showUpdateStudent(@RequestParam long id, Model model) {
    Student student = studentService.getStudentById(id);
    model.addAttribute("action", "Update");
    model.addAttribute("student", student);
    return "student/save-student";
  }

  @PostMapping("/delete-student")
  @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'STUDENT:WRITE')")
  public String deleteStudent(@RequestParam long id,
                              RedirectAttributes ra,
                              Model model) {
    ResultDto result = new ResultDto();
    try {
      studentService.deleteStudent(id);
      result.setStatus(ResultStatus.SUCCESS);
      result.setMessage("Deleted student successful!");
      ra.addFlashAttribute("result", result);
      return "redirect:/student";
    } catch (Exception e) {
      result.setStatus(ResultStatus.FAIL);
      result.setMessage(e.getMessage());
      model.addAttribute("result", result);
      model.addAttribute("students", studentService.getAllStudents());
      return "student/student-list";
    }
  }

}
