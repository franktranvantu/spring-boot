package com.franktran.jsp.course;

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
import static com.franktran.jsp.config.security.UserRole.COURSE;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENROLMENT', 'ROLE_COURSE', 'ROLE_STUDENT')")
    public String index(Model model, Authentication authentication) {
        UserRole[] editableRoles = new UserRole[] {ADMIN, COURSE};
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("isEditable", UserRole.isEditable(editableRoles, authentication.getAuthorities()));
        return "course/course-list";
    }

    @GetMapping("/create-course")
    @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'COURSE:WRITE')")
    public String showCreateCourse(@ModelAttribute("course") Course course, Model model) {
        model.addAttribute("action", "Create");
        return "course/save-course";
    }

    @PostMapping("/save-course")
    @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'COURSE:WRITE')")
    public String saveCourse(@ModelAttribute("course") @Valid Course course,
                             BindingResult bindingResult,
                             RedirectAttributes ra,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", Objects.isNull(course.getId()) ? "Create" : "Update");
            return "course/save-course";
        }
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(course.getId())) {
                model.addAttribute("action", "Create");
                courseService.createCourse(course);
                result.setMessage("Created course successful!");
            } else {
                model.addAttribute("action", "Update");
                courseService.updateCourse(course.getId(), course);
                result.setMessage("Updated course successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/course";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "course/save-course";
        }
    }

    @PostMapping("/update-course")
    @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'COURSE:WRITE')")
    public String showUpdateCourse(@RequestParam long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("course", course);
        return "course/save-course";
    }

    @PostMapping("/delete-course")
    @PreAuthorize("hasAnyAuthority('ADMIN:WRITE', 'COURSE:WRITE')")
    public String deleteCourse(@RequestParam long id,
                               RedirectAttributes ra,
                               Model model) {
        ResultDto result = new ResultDto();
        try {
            courseService.deleteCourse(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted course successful!");
            ra.addFlashAttribute("result", result);
            return "redirect:/student";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            model.addAttribute("courses", courseService.getAllCourses());
            return "course/course-list";
        }
    }
}
