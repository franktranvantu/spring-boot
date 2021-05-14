package com.franktran.datajpa.repository;

import com.franktran.datajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

  //  @Query("SELECT s FROM Student s WHERE s.email = ?1")
  Optional<Student> findStudentByEmail(String email);

}
