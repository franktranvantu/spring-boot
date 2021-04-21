package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.CourseService;
import com.franktran.jsp.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class EnrolmentController {

    private final EnrolmentService enrolmentService;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrolmentController(EnrolmentService enrolmentService,
                               CourseService courseService,
                               StudentService studentService) {
        this.enrolmentService = enrolmentService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public String index(Model model) {
        List<Enrolment> enrolments = enrolmentService.getAllEnrolments();
        model.addAttribute("enrolments", enrolments);
        return "enrolment/enrolment-list";
    }

    @GetMapping("/create-enrolment")
    public String showCreateEnrolment(@ModelAttribute("enrolment") Enrolment enrolment, Model model) {
        model.addAttribute("action", "Create");
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        return "enrolment/save-enrolment";
    }

    @PostMapping("/save-enrolment")
    public String saveEnrolment(@ModelAttribute("enrolment") Enrolment enrolment, Model model) {
        try {
            if (Objects.isNull(enrolment.getId())) {
                model.addAttribute("action", "Create");
                enrolmentService.createEnrolment(enrolment);
            } else {
                model.addAttribute("action", "Update");
                enrolmentService.updateEnrolment(enrolment.getId(), enrolment);
            }
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "enrolment/save-enrolment";
        }
    }

    @GetMapping("/update-enrolment/{id}")
    public String showUpdateEnrolment(@PathVariable int id, Model model) {
        Enrolment enrolment = enrolmentService.getEnrolmentById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("enrolment", enrolment);
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        return "enrolment/save-enrolment";
    }

    @GetMapping("/delete-enrolment/{id}")
    public String deleteEnrolment(@PathVariable int id) {
        enrolmentService.deleteEnrolment(id);
        return "redirect:/";
    }
}
