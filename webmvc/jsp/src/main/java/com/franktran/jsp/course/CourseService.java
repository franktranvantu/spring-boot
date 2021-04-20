package com.franktran.jsp.course;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  public Course getCourseById(long id) {
    return courseRepository.findById(id).orElse(null);
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public Course updateCourse(long id, Course course) {
    Course existCourse = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Course with id does not exist")));
    if (Objects.nonNull(course.getName()) && !Objects.equals(existCourse.getName(), course.getName())) {
      Optional<Course> courseByName = courseRepository.findCourseByName(course.getName());
      if (courseByName.isPresent()) {
        throw new IllegalArgumentException("Course taken");
      }
      existCourse.setName(course.getName());
    }
    return courseRepository.save(existCourse);
  }

  public void deleteCourse(long id) {
    boolean existsById = courseRepository.existsById(id);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Course with id %s not exists", id));
    }
    courseRepository.deleteById(id);
  }
}
