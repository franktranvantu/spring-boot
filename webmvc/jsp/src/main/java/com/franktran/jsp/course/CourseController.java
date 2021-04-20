package com.franktran.jsp.course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String saveCourse(@ModelAttribute("course") Course course, Model model) {
        try {
            if (Objects.isNull(course.getId())) {
                model.addAttribute("action", "Create");
                courseService.createCourse(course);
            } else {
                model.addAttribute("action", "Update");
                courseService.updateCourse(course.getId(), course);
            }
            return "redirect:/course";
        } catch (Exception e) {
            model.addAttribute("nameError", e.getMessage());
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
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return "redirect:/course";
    }
}
