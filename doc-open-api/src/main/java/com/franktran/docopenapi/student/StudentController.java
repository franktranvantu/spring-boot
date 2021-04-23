package com.franktran.docopenapi.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  @Operation(summary = "Get all students from database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Get all students from database",
          content = {
              @Content(mediaType = "application/json")
          }
      )
  })
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get the specific student by id from database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Get the specific student by id from database",
          content = {
              @Content(mediaType = "application/json")
          }
      )
  })
  public Student getStudentById(@PathVariable long id) {
    return studentService.getStudentById(id);
  }

  @PostMapping
  @Operation(summary = "Create a new student into database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Created a new student into database",
          content = {
              @Content(mediaType = "application/json")
          }
      )
  })
  public void createStudent(@RequestBody Student student) {
    studentService.createStudent(student);
  }

  @PutMapping("{id}")
  @Operation(summary = "Update an existed student into database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Updated an existed student into database",
          content = {
              @Content(mediaType = "application/json")
          }
      ),
      @ApiResponse(
          responseCode = "400",
          description = "The student does not exist in database",
          content = {
              @Content(mediaType = "text/plain")
          }
      )
  })
  public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
    studentService.updateStudent(id, student);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Delete an existed student from database")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Deleted an existed student from database",
          content = {
              @Content(mediaType = "application/json")
          }
      ),
      @ApiResponse(
          responseCode = "400",
          description = "The student does not exist in database",
          content = {
              @Content(mediaType = "text/plain")
          }
      )
  })
  public void deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
  }

}
