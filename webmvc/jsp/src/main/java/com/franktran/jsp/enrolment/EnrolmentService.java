package com.franktran.jsp.enrolment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnrolmentService {

  private final EnrolmentRepository enrolmentRepository;

  public EnrolmentService(EnrolmentRepository enrolmentRepository) {
    this.enrolmentRepository = enrolmentRepository;
  }

  public List<Enrolment> getAllEnrolments() {
    return enrolmentRepository.findAll();
  }

  public Enrolment getEnrolmentById(long id) {
    return enrolmentRepository.findById(id).orElse(null);
  }

  public Enrolment createEnrolment(Enrolment enrolment) {
    Optional<Enrolment> enrolmentByCourseIdAndStudentIdAndSemester = enrolmentRepository
        .findEnrolmentByCourseIdAndStudentIdAndSemester(enrolment.getCourse().getId(), enrolment.getStudent().getId(), enrolment.getSemester());
    if (enrolmentByCourseIdAndStudentIdAndSemester.isPresent()) {
      throw new IllegalArgumentException(String.format("Student %s taken course %s for semester %s",
          enrolment.getCourse().getName(), enrolment.getStudent().getName(), enrolment.getSemester()));
    }
    return enrolmentRepository.save(enrolment);
  }

  public Enrolment updateEnrolment(long id, Enrolment enrolment) {
    Enrolment existEnrolment = enrolmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Enrolment with id %s does not exist", id)));
    Optional<Enrolment> enrolmentByCourseIdAndStudentIdAndSemester = enrolmentRepository
        .findEnrolmentByCourseIdAndStudentIdAndSemester(enrolment.getCourse().getId(), enrolment.getStudent().getId(), enrolment.getSemester());
    // Update course
    if (Objects.nonNull(enrolment.getCourse().getId())
        && !Objects.equals(existEnrolment.getCourse().getId(), enrolment.getCourse().getId())) {
      if (enrolmentByCourseIdAndStudentIdAndSemester.isPresent()) {
        throw new IllegalArgumentException(String.format("Student %s taken course %s for semester %s",
            enrolment.getCourse().getName(), enrolment.getStudent().getName(), enrolment.getSemester()));
      }
      existEnrolment.getCourse().setId(enrolment.getCourse().getId());
    }
    // Update student
    if (Objects.nonNull(enrolment.getStudent().getId())
        && !Objects.equals(existEnrolment.getStudent().getId(), enrolment.getStudent().getId())) {
      if (enrolmentByCourseIdAndStudentIdAndSemester.isPresent()) {
        throw new IllegalArgumentException(String.format("Student %s taken course %s for semester %s",
            enrolment.getCourse().getName(), enrolment.getStudent().getName(), enrolment.getSemester()));
      }
      existEnrolment.getStudent().setId(enrolment.getStudent().getId());
    }
    // Update semester
    if (Objects.nonNull(enrolment.getSemester())
        && !Objects.equals(existEnrolment.getSemester(), enrolment.getSemester())) {
      if (enrolmentByCourseIdAndStudentIdAndSemester.isPresent()) {
        throw new IllegalArgumentException(String.format("Student %s taken course %s for semester %s",
            enrolment.getCourse().getName(), enrolment.getStudent().getName(), enrolment.getSemester()));
      }
      existEnrolment.setSemester(enrolment.getSemester());
    }
    return enrolmentRepository.save(existEnrolment);
  }

  public void deleteEnrolment(long id) {
    boolean existsById = enrolmentRepository.existsById(id);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Enrolment with id %s not exists", id));
    }
    enrolmentRepository.deleteById(id);
  }
}
