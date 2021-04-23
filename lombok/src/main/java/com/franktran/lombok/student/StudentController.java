package com.franktran.lombok.student;

import com.franktran.lombok.CustomClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents() {
        log.info("Get all students");
        CustomClass customClass = CustomClass.builder().build();
        String upperCase = CustomUtility.toUpperCase("frank");
        return Arrays.asList(
                new Student("Frank", "frank@gmail.com")
        );
    }
}
