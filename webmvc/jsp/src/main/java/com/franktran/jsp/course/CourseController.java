package com.franktran.jsp.course;

import com.franktran.jsp.dto.ResultDto;
import com.franktran.jsp.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/course-list";
    }

    @GetMapping("/create-course")
    public String showCreateCourse(@ModelAttribute("course") Course course, Model model) {
        model.addAttribute("action", "Create");
        return "course/save-course";
    }

    @PostMapping("/save-course")
    public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
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
                model.addAttribute("result", result);
            } else {
                model.addAttribute("action", "Update");
                courseService.updateCourse(course.getId(), course);
                result.setMessage("Updated course successful!");
                model.addAttribute("result", result);
            }
            result.setStatus(ResultStatus.SUCCESS);
            model.addAttribute("result", result);
            model.addAttribute("courses", courseService.getAllCourses());
            return "course/course-list";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "course/save-course";
        }
    }

    @GetMapping("/update-course/{id}")
    public String showUpdateCourse(@PathVariable int id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("course", course);
        return "course/save-course";
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable int id, Model model) {
        ResultDto result = new ResultDto();
        try {
            courseService.deleteCourse(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted course successful!");
            model.addAttribute("result", result);
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
        }
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/course-list";
    }
}
