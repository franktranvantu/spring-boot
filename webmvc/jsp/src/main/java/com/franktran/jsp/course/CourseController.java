package com.franktran.jsp.course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", null);
        return "course/course-list";
    }
}
