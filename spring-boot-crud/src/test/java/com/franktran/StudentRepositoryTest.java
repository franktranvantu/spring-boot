package com.franktran;

import com.franktran.student.Student;
import com.franktran.student.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class StudentRepositoryTest {

  @Autowired
  public StudentRepository studentRepository;

  @Before
  public void setUp() {
    List<Student> students = Arrays.asList(
        new Student(1L, "Frank", "frank@gmail.com", LocalDate.of(1992, Month.SEPTEMBER, 12)),
        new Student(2L, "Henry", "henry@gmail.com", LocalDate.of(1984, Month.JANUARY, 21)),
        new Student(3L, "Bean", "bean@gmail.com", LocalDate.of(1984, Month.APRIL, 9))
    );
    Mockito.when(studentRepository.findAll())
        .thenReturn(students);
  }

  @Test
  public void testGetStudents() {
    assertEquals(3, studentRepository.findAll().size());
  }

  @Test
  public void testGetStudentById() {
    Student frank = studentRepository.findAll().get(0);
    assertEquals(frank, studentRepository.findById(1L));
  }
}
