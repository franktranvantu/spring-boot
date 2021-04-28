package com.franktran.jsp.enrolment;

import com.franktran.jsp.course.Course;
import com.franktran.jsp.course.CourseService;
import com.franktran.jsp.dto.ResultDto;
import com.franktran.jsp.dto.ResultStatus;
import com.franktran.jsp.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String saveEnrolment(@ModelAttribute("enrolment") @Valid Enrolment enrolment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", Objects.isNull(enrolment.getId()) ? "Create" : "Update");
            return "enrolment/save-enrolment";
        }
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(enrolment.getId())) {
                model.addAttribute("action", "Create");
                enrolmentService.createEnrolment(enrolment);
                result.setMessage("Created enrolment successful!");
                model.addAttribute("result", result);
            } else {
                model.addAttribute("action", "Update");
                enrolmentService.updateEnrolment(enrolment.getId(), enrolment);
                result.setMessage("Updated enrolment successful!");
                model.addAttribute("result", result);
            }
            result.setStatus(ResultStatus.SUCCESS);
            model.addAttribute("result", result);
            model.addAttribute("enrolments", enrolmentService.getAllEnrolments());
            return "enrolment/enrolment-list";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "enrolment/enrolment-course";
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

    @PostMapping("/delete-enrolment")
    public String deleteEnrolment(@RequestParam int id, Model model) {
        ResultDto result = new ResultDto();
        try {
            enrolmentService.deleteEnrolmentById(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted enrolment successful!");
            model.addAttribute("result", result);
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
        }
        List<Enrolment> enrolments = enrolmentService.getAllEnrolments();
        model.addAttribute("enrolments", enrolments);
        return "enrolment/enrolment-list";
    }
}
